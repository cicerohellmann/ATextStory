package com.hellmann.atextstory.client

import com.hellmann.atextstory.data.Message

fun systemDefinition(theme: String): String {
    return """
        You will act as game master,
        
        The story will be about $theme,
        Your answers should be like
        {
          "scenario": {"text": "scenario here", "tag": "someTag"},
          "options": [
            {"text": "some option here", "tag": "someTag"},
            {"text": "some other option here", "tag": ""},
            {"text": "option here and on and on", "tag": ""}
          ]
        }
        where we could have an N amount of options,
        
        the game needs to end, tags should be marked as continue, death or victory. 
        The scenario should be come tagged with the same tag of the previous answer.
        In case of victory or death, there should be no options.
        
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