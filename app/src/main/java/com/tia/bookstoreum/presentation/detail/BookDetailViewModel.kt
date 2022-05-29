package com.tia.bookstoreum.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.tia.bookstoreum.data.BooksResponseItem
import com.tia.bookstoreum.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException

class BookDetailViewModel(private val data: ApiService) : ViewModel() {
    var bookDetails by mutableStateOf<BooksResponseItem?>(null)
    private set
    fun retrieveSelectedData(index: Int) = viewModelScope.launch(Dispatchers.IO) {
        val res = try {
            data.getBookResponse()
        } catch (e: Exception) {
            return@launch
        } catch (e: IOException) {
            return@launch
        } catch (e: HttpException) {
            return@launch
        }
        bookDetails = res.find {
            it.id == index
        }
    }
}