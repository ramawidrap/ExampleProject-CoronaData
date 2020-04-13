package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.di.AppComponent
import com.example.weatherapp.di.ApplicationModule
import com.example.weatherapp.di.DaggerAppComponent
import com.example.weatherapp.di.NetworkModule

class App : Application() {
    lateinit var appComponent : AppComponent

    companion object {
        lateinit var app : App
    }

    override fun onCreate() {
        super.onCreate()
        app = this

        appComponent = DaggerAppComponent.builder().applicationModule(ApplicationModule(this)).networkModule(
            NetworkModule(this)
        ).build()

    }

    fun getApplicationComponent() : AppComponent {
        return appComponent
    }
}