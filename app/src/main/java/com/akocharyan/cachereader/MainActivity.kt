package com.akocharyan.cachereader

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.akocharyan.cachereader.features.cache.presenter.CacheScreen
import com.akocharyan.core.platorm.BaseActivity
import com.akocharyan.core.ui.theme.CacheReaderTheme

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CacheReaderApp()
        }
    }

}

@Composable
fun CacheReaderApp() {
    CacheReaderTheme {
        val navController = rememberNavController()

        Scaffold { innerPadding ->
            CacheReaderNavHost(navController, modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun CacheReaderNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = CacheScreen.Cache.name,
        modifier = modifier
    ) {
        composable(CacheScreen.Cache.name) {
            CacheScreen(navController)
        }
    }
}
