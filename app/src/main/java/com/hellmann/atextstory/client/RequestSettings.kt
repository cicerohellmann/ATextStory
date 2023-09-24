package com.hellmann.atextstory.client

import com.hellmann.atextstory.BuildConfig

object RequestSettings {
    private const val apiKey: String = BuildConfig.API_KEY
    const val bearer: String = "Bearer $apiKey"
    const val path: String = "https://api.openai.com/v1/chat/completions"
    val toJSONCleaner: Regex = "[^a-zA-Z0-9 :{},\\[\\]\".'!?]".toRegex()
}