package com.example.weatherapp.di

import android.app.Application
import com.example.weatherapp.ui.fragment.InfoGempaFragment
import com.example.weatherapp.ui.fragment.ListRegionFragment
import dagger.Component

@PerActivity
@Component(dependencies = [AppComponent::class], modules = [CustomViewModelFactoryModule::class])
interface ViewModelComponent {

    fun inject(listRegionFragment : ListRegionFragment)

    fun inject(infoGempaFragment: InfoGempaFragment)

    @Component.Builder
    interface Builder{

        fun appComponent(component : AppComponent) : Builder

        fun build() : ViewModelComponent
    }



}