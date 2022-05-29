package com.tia.bookstoreum.remote

import com.tia.bookstoreum.data.BooksResponseItem

interface ApiService {
    suspend fun getBookResponse(): List<BooksResponseItem>
}