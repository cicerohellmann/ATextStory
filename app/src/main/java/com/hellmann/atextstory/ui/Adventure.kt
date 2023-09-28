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
fun Adventure(pickedTheme: String, onRequest: (Boolean) -> Unit) {
    val scope = rememberCoroutineScope()

    val storyLine = remember { mutableStateListOf(roleMessage(pickedTheme), initialUserMessage) }

    var freeOption by remember { mutableStateOf("") }
    var currentStory by remember {
        mutableStateOf(
            ScenarioData(
                scenario = "Your story is coming",
                options = listOf()
            )
        )
    }

    LaunchedEffect(currentStory){
        freeOption = ""
    }

    LaunchedEffect(key1 = Unit) {
        scope.launch {
            onRequest(true)
            currentStory = postChatCompletion(storyLine)
            onRequest(false)
            storyLine.add(Message(role = "assistant", content = currentStory.scenario))
        }
    }

    AdventureLazyColumn(
        paddingValues = PaddingValues(medium)
    ) {

        item {
            AdventureText(currentStory.scenario)
        }
        items(currentStory.options) { option ->
            LocalSpacer()
            AdventureButton(text = option) {
                scope.launch {
                    storyLine.add(Message(role = "user", content = option))
                    onRequest(true)
                    currentStory = postChatCompletion(storyLine)
                    onRequest(false)
                    storyLine.add(Message(role = "assistant", content = currentStory.scenario))
                }
            }
        }
        item {
            LocalSpacer()
            AdventureTextField(
                value = freeOption,
                label = "Choose your own path",
                onValueChange = { freeOption = it },
                onSend = {
                    scope.launch {
                        storyLine.add(Message(role = "user", content = freeOption))
                        onRequest(true)
                        currentStory = postChatCompletion(storyLine)
                        storyLine.add(Message(role = "assistant", content = currentStory.scenario))
                        onRequest(false)
                    }
                }
            )
        }
    }
}
