package com.tia.bookstoreum.utils

import androidx.datastore.preferences.core.booleanPreferencesKey

object CustomSettings {
    const val DATASTORE_NAME = "first_time_launch"
    val BOARDING_STATE = booleanPreferencesKey("boarding_states")
    const val BASE_URL = "http://192.168.1.10:8080/books"
    val genreOfBooks = mapOf(
        "Nov" to "Novel",
        "Rom" to "Romance"
    )
    val menuList = mutableListOf(
        "Novel", "Self-love", "Science", "Romance", "Crime", "Sci-fi"
    )
}