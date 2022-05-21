package com.tia.bookstoreum

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.tia.bookstoreum.ui.theme.BookStoreUMTheme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


class MainActivityKtTest{

    @get:Rule
    val composeTest = createComposeRule()

    @Test
    fun uiTest(){
        composeTest.setContent {
            BookStoreUMTheme {

            }
        }
        composeTest.onNodeWithText("Hello, World !").assertExists()
    }
}