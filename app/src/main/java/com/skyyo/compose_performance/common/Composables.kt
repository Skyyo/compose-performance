package com.skyyo.compose_performance.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TextWrapper(provideCount: () -> Int) {
    Text(text = "Count: ${provideCount()}")
}

@Composable
fun TextWrapper(offset: Int) {
    Text(text = "$offset")
}

@Composable
fun ColumnWrapper(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = content
    )
}

@Composable
fun BoxWrapper(content: @Composable BoxScope.() -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), content = content)
}