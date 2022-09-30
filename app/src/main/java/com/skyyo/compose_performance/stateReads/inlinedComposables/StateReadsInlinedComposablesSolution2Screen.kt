package com.skyyo.compose_performance.stateReads.inlinedComposables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.skyyo.compose_performance.common.TextWrapper

@Composable
fun StateReadsInlinedComposablesSolution2Screen() {
    var outerCount by remember { mutableStateOf(0) }
    Column {
        TextWrapper(provideCount = { outerCount })
        Button(onClick = { outerCount++ }) {
            Text(text = "count++")
        }
    }
}