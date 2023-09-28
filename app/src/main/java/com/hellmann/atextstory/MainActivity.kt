package com.hellmann.atextstory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.hellmann.atextstory.ui.Adventure
import com.hellmann.atextstory.ui.ThemePicking
import com.hellmann.atextstory.ui.theme.LoadingAnimation

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box {
                var isRequesting by remember {
                    mutableStateOf(false)
                }
                var pickedTheme by remember {
                    mutableStateOf("")
                }
                when (pickedTheme) {
                    "" -> ThemePicking {
                        pickedTheme = it
                    }

                    else -> Adventure(pickedTheme){
                        isRequesting = it
                    }
                }
                if (isRequesting)
                    LoadingAnimation()
            }
        }
    }
}


data class AdventureState(
    val theme: String? = null,
)