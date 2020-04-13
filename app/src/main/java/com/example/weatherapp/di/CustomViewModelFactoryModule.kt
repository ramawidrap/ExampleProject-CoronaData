package com.example.weatherapp.di

import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.viewmodel.DataViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class CustomViewModelFactoryModule {


    @Binds
    abstract fun getFactory(dataViewModelFactory: DataViewModelFactory): ViewModelProvider.Factory


}