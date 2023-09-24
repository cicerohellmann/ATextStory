package com.hellmann.atextstory.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.hellmann.atextstory.client.systemDefinition
import com.hellmann.atextstory.client.getStoryThemes
import com.hellmann.atextstory.data.Message
import com.hellmann.atextstory.data.ScenarioData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun AdventureUI(pickedTheme: String) {

    val roleMessage = Message(
        role = "system",
        content = systemDefinition(pickedTheme)
    )

    val initialUserMessage = Message(
        role = "user",
        content = "Generate a new RPG scenario."
    )

    val storyLine = mutableListOf(roleMessage, initialUserMessage)


    var freeOption by remember {
        mutableStateOf("")
    }
    val scope = CoroutineScope(Dispatchers.Main)
    var currentStory by remember {
        mutableStateOf(
            ScenarioData(
                scenario = "Your story is coming",
                options = listOf()
            )
        )
    }

    LaunchedEffect(key1 = Unit) {
        scope.launch {
            currentStory = getStoryThemes(storyLine)
        }
    }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item {
            Text(currentStory.scenario)
        }
        items(currentStory.options) {
            LocalSpacer()
            Button(onClick = {
                scope.launch {
                    storyLine.add(
                        Message(
                            role = "user",
                            content = it
                        )
                    )
                    currentStory = getStoryThemes(messages = storyLine)

                }
            }) {
                Text(it)
            }
        }
        item {
            LocalSpacer()
            TextField(
                value = freeOption,
                label = {
                    Text(text = "Choose your own path")
                },
                onValueChange = {
                    freeOption = it
                },
                keyboardActions = KeyboardActions(onSend = {
                    scope.launch {
                        storyLine.add(
                            Message(
                                role = "user",
                                content = freeOption
                            )
                        )
                        currentStory = getStoryThemes(messages = storyLine)
                    }
                }),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Send
                )
            )
        }
    }
}