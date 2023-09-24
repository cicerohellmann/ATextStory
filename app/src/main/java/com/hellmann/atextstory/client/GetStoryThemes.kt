package com.hellmann.atextstory.client

import com.hellmann.atextstory.client.RequestSettings.bearer
import com.hellmann.atextstory.client.RequestSettings.path
import com.hellmann.atextstory.client.RequestSettings.toJSONCleaner
import com.hellmann.atextstory.data.Message
import com.hellmann.atextstory.data.OpenAIRequest
import com.hellmann.atextstory.data.OpenAIResponse
import com.hellmann.atextstory.data.ScenarioData
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.serialization.json.encodeToJsonElement

suspend fun getStoryThemes(messages: List<Message>): ScenarioData {
    val response = httpClient.post(
        urlString = path
    ) {
        header("Authorization", bearer)
        setBody(json.encodeToJsonElement(OpenAIRequest(messages = messages)))
    }
    println(response)
    val realResponse =
        json.decodeFromString<OpenAIResponse>(response.body()).choices.first().message

    val cleanedJson = realResponse.content.replace(toJSONCleaner, "")

    return json.decodeFromString(cleanedJson)
}