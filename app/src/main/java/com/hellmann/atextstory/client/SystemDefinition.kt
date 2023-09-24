package com.hellmann.atextstory.client

fun systemDefinition(theme: String): String {
    return "You will act as game master, " +
            "The story will be about $theme, " +
            "Your answers should be like " +
            "{\"scenario\":\"some_scenario\",\"options\":[\"some option here\",\"some other option here\",\"another option here and on and on\"]}\n " +
            "where we could have an N amount of options, " +
            "the JSON must be unformatted, please"
}