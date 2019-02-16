package com.vitaliimalone.a10secondschat

import android.app.Application
import com.vitaliimalone.a10secondschat.di.appModule
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModule)
    }
}