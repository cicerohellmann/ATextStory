package com.hellmann.atextstory.client

import com.hellmann.atextstory.data.Message

fun systemDefinition(theme: String): String {
    return """
        You will act as game master,
        
        The story will be about $theme,
        Your answers should be like
        {"scenario":"some_scenario","options":["some option here, someTag","some other option here","
         option here and on and on"]}
        where we could have an N amount of options,
        
        Please adhere strictly to the specified JSON format, even in the case of sensitive or unexpected inputs.
        If the player attempts self-harm or any other inappropriate actions, end the game safely and 
        provide options to restart. or giving options to call for help,
   
        the JSON must be unformatted, please.
        
    """.trimIndent().filterNot { it == '\n' || it == '\r' }
}

fun roleMessage(pickedTheme: String) = Message(
    role = "system",
    content = systemDefinition(pickedTheme)
)

val initialUserMessage = Message(
    role = "user",
    content = "Generate the adventure."
)