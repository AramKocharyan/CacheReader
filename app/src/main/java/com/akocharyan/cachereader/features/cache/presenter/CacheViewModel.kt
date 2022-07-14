package com.akocharyan.cachereader.features.cache.presenter

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.provider.DocumentsContract
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.akocharyan.cachereader.features.cache.data.model.CacheDto
import com.akocharyan.cachereader.features.cache.domain.CacheUseCase
import com.akocharyan.cachereader.features.cache.domain.helper.Constants
import com.akocharyan.cachereader.features.cache.domain.helper.extensions.getAppDirectoryPermission
import com.akocharyan.cachereader.features.cache.domain.models.AppEnum
import com.akocharyan.cachereader.features.cache.presenter.model.PermissionNotGranted
import com.akocharyan.core.models.PagedList
import com.akocharyan.core.platorm.BaseViewModel
import com.akocharyan.core.util.network.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CacheViewModel @Inject constructor(
    private val cache: CacheUseCase,
    private val contentResolver: ContentResolver
) : BaseViewModel() {

    private val _cacheLiveData: MutableLiveData<PagedList<CacheDto>> = MutableLiveData(PagedList(emptyList()))
    val cacheLiveData: LiveData<PagedList<CacheDto>> = _cacheLiveData

    fun getCache(app: AppEnum, nextPage: Int = 1, pageSize: Int = PAGE_SIZE) {
        Log.d("CacheViewModel: ", "getCache")
        val permission = contentResolver.getAppDirectoryPermission(app)
        if (permission != null) {
            handleLoading(LoadingState.loading())
            cache(viewModelScope, CacheUseCase.Params(permission.uri, nextPage, pageSize)) {
                fold(::handleFailure, ::handleCacheResult)
            }
        } else {
            handleFailure(PermissionNotGranted(app))
        }
    }

    fun persistGrantedPermission(result: ActivityResult): Boolean {
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data?.data ?: return false
            takePersistablePermission(data)
            return true
        } else {
            return false
        }
    }

    fun buildGrantPermissionIntent(app: AppEnum) = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE).putExtra(
        DocumentsContract.EXTRA_INITIAL_URI,
        DocumentsContract.buildDocumentUri(
            Constants.EXTERNAL_STORAGE_PROVIDER_AUTHORITY,
            "${Constants.ANDROID_DOC_ID_PREFIX}${app.cacheDir}"
        )
    )

    private fun takePersistablePermission(uri: Uri) {
        contentResolver.takePersistableUriPermission(
            uri,
            Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
        )
    }

    private fun handleCacheResult(cache: PagedList<CacheDto>) {
        Log.d("CacheViewModel ", "handleCacheResult")
        _cacheLiveData.value = cache
        handleLoading(LoadingState.completed())
    }

    companion object {
        const val PAGE_SIZE = 20
    }

}
