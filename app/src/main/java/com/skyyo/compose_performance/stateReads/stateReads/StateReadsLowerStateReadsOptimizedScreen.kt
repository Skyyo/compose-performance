package com.skyyo.compose_performance.stateReads.stateReads

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
import com.skyyo.compose_performance.common.ColumnWrapper
import com.skyyo.compose_performance.common.TextWrapper
import com.skyyo.compose_performance.common.TypicalViewModel
import kotlinx.coroutines.flow.StateFlow

// not great because composables will be unskippable, + doesn't fix the issue with counter
@Composable
fun StateReadsLowerStateReadsOptimizedScreen(viewModel: TypicalViewModel = hiltViewModel()) {
    ColumnWrapper {
        InputsWrapperLowerState(
            nameStateFlow = viewModel.name,
            creditCardNumberStateFlow = viewModel.creditCardNumber,
            onNameEntered = viewModel::onNameEntered,
            onCreditCardNumberEntered = viewModel::onCardNumberEntered
        )
    }
}

//region related composables
@Composable
private fun InputsWrapperLowerState(
    nameStateFlow: StateFlow<String>,
    creditCardNumberStateFlow: StateFlow<String>,
    onNameEntered: (value: String) -> Unit,
    onCreditCardNumberEntered: (value: String) -> Unit,
) {
    var innerCount by remember { mutableStateOf(0) }

    NameTextFieldLowerState(nameStateFlow, onNameEntered)
    CreditCardNumberTextFieldLowerState(creditCardNumberStateFlow, onCreditCardNumberEntered)
    Text(text = "Count: $innerCount")
    Button(onClick = { innerCount++ }) {
        Text(text = "inner count++")
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
private fun NameTextFieldLowerState(
    nameStateFlow: StateFlow<String>,
    onNameEntered: (value: String) -> Unit
) {
    val name by nameStateFlow.collectAsStateWithLifecycle()

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = name,
        onValueChange = onNameEntered,
        label = { Text("name") },
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
private fun CreditCardNumberTextFieldLowerState(
    creditCardNumberStateFlow: StateFlow<String>,
    onCreditCardEntered: (value: String) -> Unit
) {
    val creditCardNumber by creditCardNumberStateFlow.collectAsStateWithLifecycle()

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = creditCardNumber,
        onValueChange = onCreditCardEntered,
        label = { Text("card number") },
    )
}
//endregion

