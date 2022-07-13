package com.akocharyan.crypto

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.akocharyan.core.platorm.BaseActivity
import com.akocharyan.core.ui.theme.CryptoTheme
import com.akocharyan.crypto.features.currencies.presenter.CacheScreen
import com.akocharyan.crypto.features.currencies.presenter.cacheGraph

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoApp()
        }
    }

}

@Composable
fun CryptoApp() {
    CryptoTheme {
        val navController = rememberNavController()

        Scaffold { innerPadding ->
            CryptoNavHost(navController, modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun CryptoNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = CacheScreen.Cache.name,
        modifier = modifier
    ) {
        cacheGraph()
    }
}

fun Context.getActivity(): MainActivity? = when (this) {
    is MainActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}
