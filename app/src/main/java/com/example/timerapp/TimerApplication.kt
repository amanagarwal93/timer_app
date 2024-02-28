package com.example.timerapp

import android.app.Application
import com.example.timerapp.domain.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 *  Timer Application includes koin modules
 */

class TimerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // adding koin modules
        startKoin {
            androidLogger()
            androidContext(this@TimerApplication)
            modules(appModule)
        }
    }
}
