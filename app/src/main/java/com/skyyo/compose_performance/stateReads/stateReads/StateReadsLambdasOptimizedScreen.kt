package com.skyyo.compose_performance.stateReads.stateReads

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skyyo.compose_performance.common.TextWrapper
import com.skyyo.compose_performance.common.TypicalViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun StateReadsLambdasOptimizedScreen(viewModel: TypicalViewModel = hiltViewModel()) {
    val name by viewModel.name.collectAsStateWithLifecycle()
    val creditCardNumber by viewModel.creditCardNumber.collectAsStateWithLifecycle()

    Column {
        InputsWrapperOptimized(
            provideName = { name },
            provideCreditCardNumber = { creditCardNumber },
            onNameEntered = viewModel::onNameEntered,
            onCreditCardNumberEntered = viewModel::onCardNumberEntered
        )
    }
}

//region optimized
@Composable
private fun InputsWrapperOptimized(
    provideName: () -> String,
    provideCreditCardNumber: () -> String,
    onNameEntered: (value: String) -> Unit,
    onCreditCardNumberEntered: (value: String) -> Unit,
) {
    var innerCount by remember {
        mutableStateOf(0)
    }
    NameTextFieldOptimized(provideName, onNameEntered)
    CreditCardNumberTextFieldOptimized(provideCreditCardNumber, onCreditCardNumberEntered)
    TextWrapper(provideCount = { innerCount })
    Button(onClick = { innerCount++ }) {
        Text(text = "inner count++")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NameTextFieldOptimized(
    provideName: () -> String,
    onNameEntered: (value: String) -> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = provideName(),
        onValueChange = onNameEntered,
        label = { Text("name") },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreditCardNumberTextFieldOptimized(
    provideCreditCardNumber: () -> String,
    onCreditCardEntered: (value: String) -> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = provideCreditCardNumber(),
        onValueChange = onCreditCardEntered,
        label = { Text("card number") },
    )
}
//endregion