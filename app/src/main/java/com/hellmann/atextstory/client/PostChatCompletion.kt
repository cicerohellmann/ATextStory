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
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

suspend fun postChatCompletion(messages: List<Message>): ScenarioData {
    val response = httpClient.post(
        urlString = path
    ) {
        header("Authorization", bearer)
        setBody(json.encodeToJsonElement(OpenAIRequest(messages = messages)))
    }
    val decodedResponse: Message?
    val decodedJson: OpenAIResponse = json.decodeFromString<OpenAIResponse>(response.body())

//    TODO(Review the JSON validation)
    return if (decodedJson.choices.first().message.content.isValidJson()) {
        decodedResponse = decodedJson.choices.first().message
        val cleanedJson = decodedResponse.content.replace(toJSONCleaner, "")
        json.decodeFromString(cleanedJson)
    } else {
        postChatCompletion(
            messages + Message(
                role = "user",
                content = "You answered it out of format, I need it in unformatted JSON, please"
            )
        )
    }
}


fun String.isValidJson(): Boolean {
    return try {
        Json.decodeFromString<JsonElement>(this)
        true
    } catch (e: SerializationException) {
        false
    }
}