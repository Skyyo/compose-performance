package com.skyyo.compose_performance.stability

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList


@Immutable
class ImmutableListWrapper(val value: List<Int>)

// showcase: unstable composable not being skipped because list is not considered stable
@Composable
fun UnstableListScreen() {
    var count by remember { mutableStateOf(1) }
    val items = remember { (0..200).map { it } }
    val immutableItems = remember { (0..200).map { it }.toImmutableList() }
    val wrappedItems = remember { ImmutableListWrapper((0..200).map { it }) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$count")
        Button(onClick = { count++ }) {
            Text(text = "count ++")
        }
        WrappedLazyColumn(items)
//        WrappedLazyColumnApproach1(wrappedItems)
//        WrappedLazyColumnApproach2(immutableItems)
    }
}

// not optimized, causing each visible item on the screen to be redrawn when count changes
@Composable
private fun WrappedLazyColumn(items: List<Int>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items = items, key = { item -> item }) { item ->
            Text(text = "item $item")
        }
    }
}

// optimized using @Immutable marked ImmutableWrapper
@Composable
private fun WrappedLazyColumnApproach1(items: ImmutableListWrapper) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items = items.value, key = { item -> item }) { item ->
            Text(text = "item $item")
        }
    }
}

// optimized using kotlinx.collections
@Composable
private fun WrappedLazyColumnApproach2(items: ImmutableList<Int>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items = items, key = { item -> item }) { item ->
            Text(text = "item $item")
        }
    }
}


