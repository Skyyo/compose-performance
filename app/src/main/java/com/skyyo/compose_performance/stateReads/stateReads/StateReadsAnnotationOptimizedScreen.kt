package com.skyyo.compose_performance.stateReads.stateReads

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skyyo.compose_performance.common.ColumnWrapper
import com.skyyo.compose_performance.common.TypicalViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun StateReadsAnnotationOptimizedScreen(viewModel: TypicalViewModel = hiltViewModel()) {
    val name by viewModel.name.collectAsStateWithLifecycle()
    val creditCardNumber by viewModel.creditCardNumber.collectAsStateWithLifecycle()

    ColumnWrapper {
        InputsWrapperAnnotation(
            name = name,
            creditCardNumber = creditCardNumber,
            onNameEntered = viewModel::onNameEntered,
            onCreditCardNumberEntered = viewModel::onCardNumberEntered
        )
    }
}

//region annotation optimized
@Composable
@NonRestartableComposable
private fun InputsWrapperAnnotation(
    name: String,
    creditCardNumber: String,
    onNameEntered: (value: String) -> Unit,
    onCreditCardNumberEntered: (value: String) -> Unit,
) {
    var innerCount by remember { mutableStateOf(0) }

    NameTextFieldAnnotation(name, onNameEntered)
    CreditCardNumberTextFieldAnnotation(creditCardNumber, onCreditCardNumberEntered)
    Text(text = "Count: $innerCount")
    Button(onClick = { innerCount++ }) {
        Text(text = "inner count++")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@NonRestartableComposable
private fun NameTextFieldAnnotation(
    name: String,
    onNameEntered: (value: String) -> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = name,
        onValueChange = onNameEntered,
        label = { Text("name") },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@NonRestartableComposable
private fun CreditCardNumberTextFieldAnnotation(
    creditCardNumber: String,
    onCreditCardEntered: (value: String) -> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = creditCardNumber,
        onValueChange = onCreditCardEntered,
        label = { Text("card number") },
    )
}

//endregion