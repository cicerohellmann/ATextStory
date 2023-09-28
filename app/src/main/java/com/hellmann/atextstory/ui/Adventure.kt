package com.hellmann.atextstory.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.hellmann.atextstory.client.postChatCompletion
import com.hellmann.atextstory.client.initialUserMessage
import com.hellmann.atextstory.client.roleMessage
import com.hellmann.atextstory.data.Message
import com.hellmann.atextstory.data.Option
import com.hellmann.atextstory.data.ScenarioData
import com.hellmann.atextstory.ui.theme.AdventureButton
import com.hellmann.atextstory.ui.theme.AdventureLazyColumn
import com.hellmann.atextstory.ui.theme.AdventureText
import com.hellmann.atextstory.ui.theme.AdventureTextField
import com.hellmann.atextstory.ui.theme.LocalSpacer
import com.hellmann.atextstory.ui.theme.Size.medium
import kotlinx.coroutines.launch


@ExperimentalMaterial3Api
@Composable
fun Adventure(pickedTheme: String, onRestart: (String) -> Unit) {
    val scope = rememberCoroutineScope()

    val storyLine = remember { mutableStateListOf(roleMessage(pickedTheme), initialUserMessage) }

    var freeOption by remember { mutableStateOf("") }
    var currentStory by remember {
        mutableStateOf(
            ScenarioData(
                scenario = Option("Your story is coming", "beginning"),
                options = listOf()
            )
        )
    }

    LaunchedEffect(currentStory) {
        freeOption = ""
    }

    LaunchedEffect(key1 = Unit) {
        scope.launch {
            currentStory = postChatCompletion(storyLine)
            storyLine.add(Message(role = "assistant", content = currentStory.scenario.toString()))
        }
    }

    AdventureLazyColumn(
        paddingValues = PaddingValues(medium)
    ) {

        item {
            AdventureText(currentStory.scenario.text)
        }
        items(currentStory.options) { option ->
            LocalSpacer()
            AdventureButton(text = option.text) {
                scope.launch {
                    storyLine.add(Message(role = "user", content = option.toString()))
                    currentStory = postChatCompletion(storyLine)
                    storyLine.add(
                        Message(
                            role = "assistant",
                            content = currentStory.scenario.toString()
                        )
                    )
                }
            }
        }
        item {
            LocalSpacer()
            if (currentStory.scenario.tag == "death" || currentStory.scenario.tag == "victory") {
                AdventureButton(text = "Restart") {
                    onRestart("")
                }
            } else {
                AdventureTextField(
                    value = freeOption,
                    label = "Choose your own path",
                    onValueChange = { freeOption = it },
                    onSend = {
                        scope.launch {
                            storyLine.add(Message(role = "user", content = freeOption))
                            currentStory = postChatCompletion(storyLine)
                            storyLine.add(
                                Message(
                                    role = "assistant",
                                    content = currentStory.scenario.toString()
                                )
                            )
                        }
                    }
                )
            }
        }
    }
}
