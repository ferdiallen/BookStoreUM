package com.tia.bookstoreum.presentation.dashboard

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tia.bookstoreum.data.BooksResponseItem
import com.tia.bookstoreum.remote.ApiService
import com.tia.bookstoreum.utils.CustomSettings
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DashboardViewModel(
    private val client: ApiService
) : ViewModel() {
    val booksData = mutableStateListOf<BooksResponseItem>()
    val selectedItem = mutableStateListOf<String>()
    init {
        retrieveData()
    }

    private fun retrieveData() = viewModelScope.launch(Dispatchers.IO) {
        val result = try {
            client.getBookResponse()
        } catch (e: Exception) {
            return@launch
        } catch (e: IOException) {
            return@launch
        }
        booksData.addAll(result)
    }

}