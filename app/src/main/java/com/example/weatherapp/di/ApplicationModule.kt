package com.example.weatherapp.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.ListRegionFragment
import com.example.weatherapp.network.service.DataService
import com.example.weatherapp.viewmodel.DataViewModelFactory
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule constructor(private val application : Application) {

    companion object {
        const val BASE_URL = "https://api.kawalcorona.com/"
    }

    @Singleton
    @Provides
    fun getApplication() : Application {
        return application
    }




}

@Singleton
@Component(modules = [ApplicationModule::class,NetworkModule::class,CustomViewModelFactoryModule::class])
interface AppComponent {
    fun inject(listRegionFragment : ListRegionFragment)
}