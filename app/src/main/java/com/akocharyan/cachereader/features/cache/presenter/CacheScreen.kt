package com.akocharyan.cachereader.features.cache.presenter

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.akocharyan.cachereader.features.cache.data.model.CacheDto
import com.akocharyan.cachereader.features.cache.domain.models.AppEnum
import com.akocharyan.cachereader.features.cache.presenter.CacheViewModel.Companion.PAGE_SIZE
import com.akocharyan.cachereader.features.cache.presenter.model.PermissionNotGranted
import com.akocharyan.core.util.network.LoadingState

enum class CacheScreen {
    Cache
}

@Composable
fun CacheScreen(navController: NavController) {
    val viewModel: CacheViewModel = hiltViewModel()
    val app = AppEnum.TELEGRAM

    LaunchedEffect(app) {
        viewModel.getCache(app)
    }

    val cacheState by viewModel.cacheLiveData.observeAsState()
    val cacheError by viewModel.failure.collectAsState()

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (viewModel.persistGrantedPermission(result)) viewModel.getCache(app)
    }

    when (cacheError) {
        is PermissionNotGranted -> {
            SideEffect {
                launcher.launch(viewModel.buildGrantPermissionIntent((cacheError as PermissionNotGranted).app))
            }
        }
        else -> Unit
    }

    val cache = cacheState
    val cacheList = remember { mutableStateListOf<CacheDto>() }
    if (cache != null) {
        cacheList.addAll(cache.data)
    }

    Scaffold {
        Column {
            Column(Modifier.padding(12.dp)) {
                Text(text = "Cache", style = MaterialTheme.typography.h5)
            }
            LazyColumn {
                itemsIndexed(cacheList) { index, cacheItem ->
                    Column(Modifier.fillParentMaxWidth()) {
                        if (viewModel.loading.value.status != LoadingState.Status.LOADING
                            && (cache?.nextPage ?: 0) > 1
                            && index > ((cache?.nextPage ?: 2) - 1) * PAGE_SIZE - 5
                        ) {
                            Log.d("CacheScreen ", "cache?.nextPage?.let { viewModel.getCache(app, it) }")
                            cache?.nextPage?.let { viewModel.getCache(app, it) }
                        }
                        CacheItem(
                            modifier = Modifier.fillParentMaxWidth(),
                            item = cacheItem,
                        )
                        Divider(color = Color.Black)
                    }
                }
            }
        }
    }
}

@Composable
private fun CacheItem(
    modifier: Modifier = Modifier,
    item: CacheDto,
) {
    Row(
        modifier = modifier.padding(top = 12.dp, bottom = 12.dp)
    ) {
        Spacer(Modifier.width(24.dp))
        Column {
            Text(
                text = item.name,
                style = MaterialTheme.typography.h6
            )
        }
    }
}
