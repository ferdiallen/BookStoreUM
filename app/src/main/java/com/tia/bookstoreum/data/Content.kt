package com.tia.bookstoreum.data

import kotlinx.serialization.Serializable

@Serializable
data class Content(
    val contentId: Int,
    val contentTitle: String,
    val contentSubTitle: String,
    val content: String,
    val contentPrice: Int
)