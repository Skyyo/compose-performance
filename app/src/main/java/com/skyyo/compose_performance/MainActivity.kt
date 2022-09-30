package com.skyyo.compose_performance

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import com.skyyo.compose_performance.stateReads.ModifierLambdasScreen
import com.skyyo.compose_performance.stateReads.stateReads.StateReadsLambdasOptimizedScreen
import com.skyyo.compose_performance.stateReads.stateReads.StateReadsAnnotationOptimizedScreen
import com.skyyo.compose_performance.stateReads.stateReads.StateReadsLowerStateReadsOptimizedScreen
import com.skyyo.compose_performance.ui.theme.ComposeperformanceTheme

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeperformanceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

//                    UnstableClassScreen()
//                    UnstableLambdaScreen()
//                    UnstableListScreen()

//                    StateReadsInlinedComposablesScreen()
//                    StateReadsInlinedComposablesSolution2Screen()

//                    StateReadsUnoptimizedScreen()
//                    StateReadsLambdasOptimizedScreen()
//                    StateReadsAnnotationOptimizedScreen()
//                    StateReadsLowerStateReadsOptimizedScreen()
                    ModifierLambdasScreen()
                }
            }
        }
    }
}
