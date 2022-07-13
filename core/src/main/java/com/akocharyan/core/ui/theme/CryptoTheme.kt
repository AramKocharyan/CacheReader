package com.akocharyan.core.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun CacheReaderTheme(content: @Composable () -> Unit) {

    MaterialTheme(colors = ColorPalette, content = content)
}
