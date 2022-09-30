package com.skyyo.compose_performance.stateReads

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.skyyo.compose_performance.common.BoxWrapper

@Composable
fun ModifierLambdasScreen() {
    val scrollState = rememberScrollState()
    BoxWrapper {
        ScrollingArea(scrollState)
//        HorizontallyMovingButtonUnoptimized(scrollState.value * 1.5f)
        HorizontallyMovingButtonOptimized(scrollProvider = { scrollState.value * 1.5f })
    }
}

@Composable
private fun ScrollingArea(scrollState: ScrollState) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .height(2000.dp)
    )
}

@Composable
private fun HorizontallyMovingButtonUnoptimized(scrollOffset: Float) {
    Button(
        modifier = Modifier
            .size(56.dp)
            .graphicsLayer {
                translationX = scrollOffset
            },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan),
        onClick = {}
    ) {}
}

@Composable
private fun HorizontallyMovingButtonOptimized(scrollProvider: () -> Float) {
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Blue,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        )
    )
    Button(
        modifier = Modifier
            .size(56.dp)
            //bad
            //.background(color)
            //good
            .drawBehind { drawCircle(color) }
            .graphicsLayer { translationX = scrollProvider() },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
        onClick = {}
    ) {}
}
