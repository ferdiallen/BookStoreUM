package com.tia.bookstoreum.remote

import android.util.Log
import coil.network.HttpException
import com.tia.bookstoreum.data.BooksResponseItem
import com.tia.bookstoreum.utils.CustomSettings
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.utils.io.errors.*

class ApiServiceImpl(private val remote: HttpClient) : ApiService {
    override suspend fun getBookResponse(): List<BooksResponseItem> {
        return try {
            remote.get { url(CustomSettings.BASE_URL) }.body()
        } catch (e: Exception) {
            Log.d("TAG", e.message.toString())
            emptyList()
        } catch (e: IOException) {
            Log.d("TAG", e.message.toString())
            emptyList()
        } catch (e: HttpException) {
            Log.d("TAG", e.message.toString())
            emptyList()
        }
    }

}