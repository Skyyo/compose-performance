package com.skyyo.compose_performance.stability

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import com.skyyo.compose_performance.common.TypicalViewModel


// showcase: unstable composables not being skipped because using unstable lambdas
@Composable
fun UnstableLambdaScreen(viewModel: TypicalViewModel = hiltViewModel()) {
    var count by remember { mutableStateOf(1) }
    val focusRequester = LocalFocusManager.current
    val rememberedOnNameEnteredClick: (value: String) -> Unit = remember(viewModel) {
        return@remember viewModel::onNameEntered
    }
    val clearFocus = remember(viewModel) { { focusRequester.clearFocus() } }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$count")

        // unstable
        Button(onClick = { viewModel.onContinueClick() }) {
            Text(text = "btn with unstable lambda")
        }
        // unstable
        Button(onClick = { focusRequester.clearFocus() }) {
            Text(text = "btn with unstable lambda")
        }
        // stable
        Button(onClick = viewModel::onContinueClick) {
            Text(text = "btn with stable lambda")
        }
        // stable
        Button(onClick = {
            rememberedOnNameEnteredClick("value passed from ui")
            clearFocus()
            someTopLvlFunction()
        }) {
            Text(text = "btn with stable lambdas")
        }

        Button(onClick = { count++ }) {
            Text(text = "press to trigger recomposition in parent scope")
        }
    }
}

fun someTopLvlFunction() {

}