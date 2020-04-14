package com.example.weatherapp.di

import android.app.Application
import android.content.Context
import com.example.weatherapp.network.service.BmkgService
import com.example.weatherapp.network.service.DataService
import com.example.weatherapp.ui.fragment.InfoGempaFragment
import com.example.weatherapp.ui.fragment.ListRegionFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {

    fun getDataService() : DataService

    fun getBmkgSerive() : BmkgService


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getApp(context: Context) : Builder

        fun build() : AppComponent
    }
}