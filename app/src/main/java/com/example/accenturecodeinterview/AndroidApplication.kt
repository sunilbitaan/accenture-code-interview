package com.example.accenturecodeinterview

/**
 * Create By Sunil Kumar
 */
import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

@HiltAndroidApp
class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(androidContext = this@AndroidApplication)
            modules(listOf())
        }
    }
}