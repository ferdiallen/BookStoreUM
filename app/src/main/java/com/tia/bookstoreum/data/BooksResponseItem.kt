package com.tia.bookstoreum.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BooksResponseItem(
    val id: Int,
    val about: String,
    val author: String,
    val title: String,
    @SerialName("body") val body: String,
    val publish: Int,
    val cover: String,
    val category: String,
    val rating: Double,
    val explicit: Boolean,
    val content: List<Content>
)