package com.tia.bookstoreum.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.tia.bookstoreum.utils.CustomSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class DataStoreBoardingSettings(private val store: DataStore<Preferences>) {

    suspend fun saveData(data: Boolean) {
        store.edit {
            it[CustomSettings.BOARDING_STATE] = data
        }
    }

    suspend fun deleteData() {
        store.edit {
            it.clear()
        }
    }

    val readLatestData = store.data.map {
            it[CustomSettings.BOARDING_STATE] ?: false
        }
}