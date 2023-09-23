package com.hellmann.atextstory.data

import kotlinx.serialization.Serializable

@Serializable
data class ScenarioData(
    val scenario: String,
    val options: List<String>
)