package com.hellmann.atextstory.data

import com.hellmann.atextstory.GPTModel
import com.hellmann.atextstory.data.Message
import kotlinx.serialization.Serializable

@Serializable
data class OpenAIRequest(
    val model: String = GPTModel.gpt_4,
    val messages: List<Message>
)