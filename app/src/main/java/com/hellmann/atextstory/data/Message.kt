package com.hellmann.atextstory.data

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val role: String,
    val content: String
)