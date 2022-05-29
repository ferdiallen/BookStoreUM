package com.tia.bookstoreum.presentation.boarding

import com.tia.bookstoreum.R

sealed class OnboardingSlides(
    val image: Int,
    val mainTitle: String,
    val contentTitle: String
) {
    object firstScreen : OnboardingSlides(
        R.drawable.img_first,
        "Read your favorite books",
        "All your favourites book in one place, read any book, staying at home, " +
                "on travelling, or anywhere else"
    )

    object secondScreen : OnboardingSlides(
        R.drawable.img_second,
        "More Than 10.000 +++ Books",
        "You can choose How many books from 10.000  books, read any book," +
                " staying at home, on travelling, or anywhere else"
    )

    object thirdScreen : OnboardingSlides(
        R.drawable.img_third,
        "Start Reading The Books",
        "Now You can Start Read in one place, read any book, staying at home, " +
                "on travelling, or anywhere else"
    )
}
