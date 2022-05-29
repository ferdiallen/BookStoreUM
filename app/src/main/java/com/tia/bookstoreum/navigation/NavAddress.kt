package com.tia.bookstoreum.navigation

sealed class NavAddress(val route: String) {
    object MainScreen : NavAddress("main_screen")
    object ReadBookScreen : NavAddress("read_book_screen")
    object BookDetailsScreen : NavAddress("book_detail_screen")
    object BoardingScreen : NavAddress("boarding_screen")
}
