package com.tia.bookstoreum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tia.bookstoreum.presentation.detail.BookDetail
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
                    /*ReadBookScreen()*/
                    BookDetail(
                        "Catcher in the Rye",
                        "J.D Salinger",
                        4F,
                        coverImage = "https://upload.wikimedia.org/wikipedia/id/3/32/Rye_catcher.jpg",
                        aboutAuthorText = "J.D. Salinger was an American writer, " +
                                "best known for his 1951 novel The Catcher in the Rye." +
                                " Before its publication, Salinger published several short stories " +
                                "in Story magazine",
                        overviewBook = "The Catcher in the Rye is a " +
                                "novel by J. D. Salinger, partially published in serial form in " +
                                "1945–1946 and as a novel in 1951. It was originally intended for " +
                                "adults but is often read by adolescents for its theme of angst," +
                                " alienation and as a critique......",
                        price = "14.99"
                    )
                }
            }
        }
    }
}
