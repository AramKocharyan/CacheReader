package com.akocharyan.crypto.features.currencies.presenter

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.akocharyan.crypto.features.currencies.data.model.CacheDto
import com.akocharyan.crypto.features.currencies.domain.models.AppEnum
import com.akocharyan.crypto.features.currencies.presenter.model.PermissionNotGranted

enum class CacheScreen {
    Cache
}

@Composable
fun CurrenciesBody(viewModel: CacheViewModel = hiltViewModel()) {

    val app = AppEnum.TELEGRAM

    viewModel.getCache(app)
    val cache by viewModel.cacheFlow.collectAsState()
    val cacheError by viewModel.failure.collectAsState()

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        viewModel.handlePermissionGrantedResult(result, app)
    }

    when (cacheError) {
        is PermissionNotGranted -> {
            SideEffect {
                launcher.launch(viewModel.buildGrantPermissionIntent((cacheError as PermissionNotGranted).app))
            }
        }
        else -> Unit
    }

    Column {
        Column(Modifier.padding(12.dp)) {
            Text(text = "Cache", style = MaterialTheme.typography.h5)
        }
        LazyColumn {
            items(cache) { currencyItem ->
                Column(Modifier.fillParentMaxWidth()) {
                    CacheItem(
                        modifier = Modifier.fillParentMaxWidth(),
                        item = currencyItem,
                    )
                    Divider(color = Color.Black)
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

fun NavGraphBuilder.cacheGraph() {
    composable(CacheScreen.Cache.name) {
        CurrenciesBody()
    }
}
