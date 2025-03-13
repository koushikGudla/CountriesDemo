package com.example.countriesdemo

import android.app.Application
import com.example.countriesdemo.di.AppModule
import com.example.countriesdemo.di.AppModuleImpl

class CountryApp: Application() {
    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }
}