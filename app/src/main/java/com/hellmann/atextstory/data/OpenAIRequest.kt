package com.hellmann.atextstory.data

import com.hellmann.atextstory.client.GPTModel
import kotlinx.serialization.Serializable

@Serializable
data class OpenAIRequest(
    val model: String = GPTModel.gpt_4,
    val messages: List<Message>
)