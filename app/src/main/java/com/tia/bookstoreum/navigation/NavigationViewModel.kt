package com.tia.bookstoreum.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tia.bookstoreum.data.datastore.DataStoreBoardingSettings
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class NavigationViewModel(dataStore: DataStoreBoardingSettings) : ViewModel() {
    var isOpenedAndAccepted by mutableStateOf(false)

    init {
        runBlocking {
            isOpenedAndAccepted = dataStore.readLatestData.first()
        }
    }
}