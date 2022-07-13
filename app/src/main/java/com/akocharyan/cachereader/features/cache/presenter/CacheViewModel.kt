package com.akocharyan.cachereader.features.cache.presenter

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.provider.DocumentsContract
import androidx.activity.result.ActivityResult
import androidx.lifecycle.viewModelScope
import com.akocharyan.cachereader.features.cache.data.model.CacheDto
import com.akocharyan.cachereader.features.cache.domain.CacheUseCase
import com.akocharyan.cachereader.features.cache.domain.helper.Constants
import com.akocharyan.cachereader.features.cache.domain.helper.extensions.getAppDirectoryPermission
import com.akocharyan.cachereader.features.cache.domain.models.AppEnum
import com.akocharyan.cachereader.features.cache.presenter.model.PermissionNotGranted
import com.akocharyan.core.platorm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CacheViewModel @Inject constructor(
    private val cache: CacheUseCase,
    private val contentResolver: ContentResolver
) : BaseViewModel() {

    private val _cacheFlow: MutableStateFlow<List<CacheDto>> = MutableStateFlow(emptyList())
    val cacheFlow: StateFlow<List<CacheDto>> = _cacheFlow

    fun getCache(app: AppEnum) {
        val permission = contentResolver.getAppDirectoryPermission(app)
        if (permission != null) {
            cache(viewModelScope, CacheUseCase.Params(permission.uri)) { fold(::handleFailure, ::handleCache) }
        } else {
            handleFailure(PermissionNotGranted(app))
        }
    }

    fun handlePermissionGrantedResult(result: ActivityResult, app: AppEnum) {
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data?.data ?: return
            takePersistablePermission(data)
            getCache(app)
        } else {
            handleFailure(PermissionNotGranted(app))
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

    private fun handleCache(cache: List<CacheDto>) {
        _cacheFlow.value = cache
    }

}
