package com.hellmann.atextstory.ui.theme

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AdventureText(text: String) {
    Text(text, Modifier.fillMaxWidth())
}

@Preview
@Composable
fun AdventureTextPreview(){
    AdventureButton("Test"){
    }
}