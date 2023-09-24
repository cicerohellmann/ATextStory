package com.hellmann.atextstory.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LocalSpacer(size: Dp = Size.medium) {
    Spacer(modifier = Modifier.size(size))
}

object Size {
    val small = 4.dp
    val medium = 16.dp
    val big = 32.dp
}