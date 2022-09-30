package com.skyyo.compose_performance.stateReads.inlinedComposables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.skyyo.compose_performance.common.ColumnWrapper
import com.skyyo.compose_performance.common.TextWrapper


@Composable
fun StateReadsInlinedComposablesScreen() {
    var outerCount by remember { mutableStateOf(0) }

    //use WrappedColumn to fix state reads from too high scope
    Column {
        Text(text = "count: $outerCount")
        Button(onClick = { outerCount++ }) {
            Text(text = "count++")
        }
    }
}
