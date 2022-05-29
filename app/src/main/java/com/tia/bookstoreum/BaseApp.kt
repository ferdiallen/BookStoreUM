package com.tia.bookstoreum

import android.app.Application
import com.tia.bookstoreum.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(androidContext = this@BaseApp)
            modules(appModules)
        }
    }
}