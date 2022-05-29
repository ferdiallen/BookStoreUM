package com.tia.bookstoreum.presentation.boarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tia.bookstoreum.data.datastore.DataStoreBoardingSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class BoardingViewModel(
    private val store: DataStoreBoardingSettings
) : ViewModel() {
    val pageList = listOf(
        OnboardingSlides.firstScreen,
        OnboardingSlides.secondScreen,
        OnboardingSlides.thirdScreen
    )

    val isAcceptedOnce = store.readLatestData

    fun hasAccepted() = viewModelScope.launch(Dispatchers.IO) {
        store.saveData(true)
    }

}