package com.akocharyan.crypto.features.currencies.presenter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.akocharyan.crypto.features.currencies.domain.model.Currency
import com.akocharyan.crypto.features.currencies.presenter.details.CurrencyDetailsBody

enum class CurrenciesScreen {
    Currencies,
    CurrencyDetails
}

@Composable
fun CurrenciesBody(
    onCurrencyClick: (Currency) -> Unit = {},
    viewModel: CurrenciesViewModel = hiltViewModel(),
) {
    val currencies by viewModel.currenciesFlow.collectAsState()

    Column {
        Column(Modifier.padding(12.dp)) {
            Text(text = "Currencies", style = MaterialTheme.typography.h5)
        }
        LazyColumn {
            items(currencies) { currencyItem ->
                Column(Modifier.fillParentMaxWidth()) {
                    CurrencyItem(
                        modifier = Modifier.fillParentMaxWidth(),
                        item = currencyItem,
                        onItemClicked = onCurrencyClick
                    )
                    Divider(color = Color.Black)
                }
            }
        }
    }
}

@Composable
private fun CurrencyItem(
    modifier: Modifier = Modifier,
    item: Currency,
    onItemClicked: (Currency) -> Unit
) {
    Row(
        modifier = modifier
            .clickable { onItemClicked(item) }
            .padding(top = 12.dp, bottom = 12.dp)
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

fun NavGraphBuilder.currenciesGraph(navController: NavController) {
    composable(CurrenciesScreen.Currencies.name) {
        CurrenciesBody(
            onCurrencyClick = { currency ->
                navigateToCurrencyDetails(navController, currency.id)
            },
        )
    }
    val currencyDetails = CurrenciesScreen.CurrencyDetails.name
    composable(
        route = "$currencyDetails/{id}",
        arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
            }
        ),
        deepLinks = listOf(
            navDeepLink {
                uriPattern = "crypto://$currencyDetails/{id}"
            }
        ),
    ) { entry ->
        val currencyId = entry.arguments?.getString("id") ?: return@composable
        CurrencyDetailsBody(currencyId)
    }
}

private fun navigateToCurrencyDetails(navController: NavController, currencyId: String) {
    navController.navigate("${CurrenciesScreen.CurrencyDetails.name}/${currencyId}")
}
