package com.hellmann.atextstory.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Choice(
    val message: Message,
    @SerialName("finish_reason")
    val finishReason: String?,
    val index: Int
)
