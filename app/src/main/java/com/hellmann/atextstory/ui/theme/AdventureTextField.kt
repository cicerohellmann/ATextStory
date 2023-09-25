package com.hellmann.atextstory.ui.theme

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdventureTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    onSend: (KeyboardActionScope.() -> Unit)?
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        label = {
            Text(text = label)
        },
        onValueChange = onValueChange,
        keyboardActions = KeyboardActions(onSend = onSend),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Send
        )
    )
}