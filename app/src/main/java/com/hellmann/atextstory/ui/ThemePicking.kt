package com.hellmann.atextstory.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.hellmann.atextstory.ui.theme.AdventureButton
import com.hellmann.atextstory.ui.theme.AdventureLazyColumn
import com.hellmann.atextstory.ui.theme.AdventureTextField
import com.hellmann.atextstory.ui.theme.Size.medium
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemePicking(onThemeChanged: (String) -> Unit) {
    val scope = rememberCoroutineScope()
    var theme by remember {
        mutableStateOf("")
    }
    Scaffold(
        modifier = Modifier.padding(medium),
        bottomBar = {
            AdventureButton(text = "Continue") {
                scope.launch {
                    onThemeChanged(theme)
                }
            }
        },

        ) {
        AdventureLazyColumn(
            paddingValues = it
        ) {
            item {
                AdventureTextField(
                    value = theme,
                    label = "Choose a theme",
                    onValueChange = {
                        theme = it
                    },
                    onSend = {
                        scope.launch {
                            onThemeChanged(theme)
                        }
                    }
                )
            }
        }
    }
}

