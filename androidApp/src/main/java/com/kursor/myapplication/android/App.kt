package com.kursor.myapplication.android

import android.app.Application
import android.content.Context
import com.kursor.myapplication.storage.storageModule
import org.koin.core.Koin
import org.koin.core.context.startKoin

class App : Application() {

    lateinit var koin: Koin

    override fun onCreate() {
        super.onCreate()
        koin = Koin().apply {
            loadModules(listOf(storageModule))
            declare(this@App as Context)
        }
    }

}