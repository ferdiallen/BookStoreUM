package com.tia.bookstoreum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tia.bookstoreum.presentation.read.ReadBookScreen
import com.tia.bookstoreum.ui.theme.BackgroundColor
import com.tia.bookstoreum.ui.theme.BookStoreUMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val controller = rememberSystemUiController()
            SideEffect {
                controller.setStatusBarColor(BackgroundColor, darkIcons = true)
            }
            BookStoreUMTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ReadBookScreen()
                }
            }
        }
    }
}
