package com.hellmann.atextstory.data

import kotlinx.serialization.Serializable

@Serializable
data class ScenarioData(
    val scenario: Option,
    val options: List<Option>
)

@Serializable
data class Option(
    val text: String,
    val tag: String
)