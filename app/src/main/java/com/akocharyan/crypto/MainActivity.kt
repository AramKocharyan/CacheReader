package com.akocharyan.crypto

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
import com.akocharyan.crypto.features.currencies.ui.CurrenciesScreen
import com.akocharyan.crypto.features.currencies.ui.currenciesGraph

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
        startDestination = CurrenciesScreen.Currencies.name,
        modifier = modifier
    ) {
        currenciesGraph(navController)
    }
}
