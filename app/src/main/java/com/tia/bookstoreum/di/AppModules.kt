package com.tia.bookstoreum.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tia.bookstoreum.data.datastore.DataStoreBoardingSettings
import com.tia.bookstoreum.navigation.NavigationViewModel
import com.tia.bookstoreum.presentation.boarding.BoardingViewModel
import com.tia.bookstoreum.presentation.dashboard.DashboardViewModel
import com.tia.bookstoreum.presentation.detail.BookDetailViewModel
import com.tia.bookstoreum.remote.ApiService
import com.tia.bookstoreum.remote.ApiServiceImpl
import com.tia.bookstoreum.utils.CustomSettings
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.compose.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single {
        PreferenceDataStoreFactory.create(
            produceFile = {
                androidContext().preferencesDataStoreFile(CustomSettings.DATASTORE_NAME)
            }, scope = CoroutineScope(Dispatchers.IO + Job())
        )
    }
    single {
        DataStoreBoardingSettings(get())
    }
    viewModel {
        BoardingViewModel(get())
    }
    viewModel {
        NavigationViewModel(get())
    }
    single {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json()
            }
        }
    }
    single<ApiService> {
        ApiServiceImpl(get())
    }
    viewModel {
        DashboardViewModel(get())
    }
    viewModel {
        BookDetailViewModel(get())
    }

}