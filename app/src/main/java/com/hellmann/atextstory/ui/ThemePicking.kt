package com.hellmann.atextstory.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemePicking(onThemeChanged: (String) -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    var theme by remember {
        mutableStateOf("")
    }
    Column {
        TextField(
            value = theme,
            label = {
                Text(text = "Choose a theme")
            },
            onValueChange = {
                theme = it
            },
            keyboardActions = KeyboardActions(onSend = {
                coroutineScope.launch {
                    onThemeChanged(theme)
                }
            }),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Send
            )
        )

    }
}