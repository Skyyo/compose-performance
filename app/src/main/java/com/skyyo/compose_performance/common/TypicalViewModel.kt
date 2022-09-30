package com.skyyo.compose_performance.common

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.skyyo.compose_performance.stability.UnstableUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class TypicalViewModel @Inject constructor(
    private val handle: SavedStateHandle
) : ViewModel() {

    val name = handle.getStateFlow("name", "")
    val creditCardNumber = handle.getStateFlow("ccNumber", "")

    //    val user = User(0, "Tor", "false")
    val user = MutableStateFlow(UnstableUser(0, "Tor", false))

    fun onNameEntered(input: String) {
        handle["name"] = input
        handle["nameErrorId"] = null
    }

    fun onCardNumberEntered(input: String) {
        handle["ccNumber"] = input
        handle["ccNumberError"] = null
    }

    fun onNameImeActionClick() {

    }

    fun onContinueClick() {

    }
}
