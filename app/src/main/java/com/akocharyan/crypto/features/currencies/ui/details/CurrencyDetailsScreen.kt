package com.akocharyan.crypto.features.currencies.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.akocharyan.core.ui.components.AnimatedCircle
import com.akocharyan.core.ui.theme.ColorPalette
import com.akocharyan.crypto.R

@Composable
fun CurrencyDetailsBody(
    currencyId: String,
    modifier: Modifier = Modifier,
    viewModel: CurrencyDetailsViewModel = hiltViewModel()
) {
    val currency = viewModel.currencyLiveData.value

    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Box(Modifier.padding(16.dp)) {
            AnimatedCircle(
                listOf(1.0f),
                listOf(ColorPalette.primary),
                Modifier
                    .height(300.dp)
                    .align(Alignment.Center)
                    .fillMaxWidth()
            )
            Column(modifier = Modifier.align(Alignment.Center)) {
                Text(
                    text = stringResource(R.string.currency_details_title),
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    text = currencyId,
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
        Spacer(Modifier.height(10.dp))
    }
}
