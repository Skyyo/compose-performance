package com.skyyo.compose_performance.stability

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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


// wrapper approach
@Immutable
data class ImmutableWrapper(
    val value: UnstableUser,
)

// considered unstable by compiler since var is used
data class UnstableUser(
    val id: Long,
    val name: String,
    var somethingUnstable: Boolean
)

// showcase: unstable composable not being skipped because UnstableUser is unstable class
@Composable
fun UnstableClassScreen() {
    val user = UnstableUser(0, "John", true)
    val immutableWrapper = ImmutableWrapper(user)
    var count by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$count")
        UserDetails(user = user)

        // UserDetailsStable and UserDetails are skippable
        UserDetailsStable(wrapper = immutableWrapper)
        UserDetails(id = immutableWrapper.value.id, name = immutableWrapper.value.name)

        Button(onClick = { count++ }) {
            Text(text = "count ++")
        }
    }
}

// Might be not skippable if UnstableUser is unstable
@Composable
private fun UserDetails(user: UnstableUser) {
    Text(text = "name: ${user.name} id:${user.id}")
}

// Stable since we pass two immutable fields
@Composable
private fun UserDetails(id: Long, name: String) {
    Text(text = "name: $name id: $id")
}

// Stable since ImmutableWrapper is marked as @Immutable
@Composable
private fun UserDetailsStable(wrapper: ImmutableWrapper) {
    Text(text = "name: ${wrapper.value.name} id:${wrapper.value.id}")
}

