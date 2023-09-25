package com.hellmann.atextstory.ui.theme

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AdventureButton(text: String, onClick: () -> Unit) {
    Button(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(5.dp), onClick = onClick) {
        Text(text)
    }
}