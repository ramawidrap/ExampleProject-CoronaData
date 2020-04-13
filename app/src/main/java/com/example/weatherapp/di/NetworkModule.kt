package com.example.weatherapp.di

import android.content.Context
import com.example.weatherapp.network.HandleNetwork
import com.example.weatherapp.network.service.DataService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule (private val context : Context) {

    @Singleton
    @Provides
    fun getContext() : Context {
        return context
    }

    @Singleton
    @Provides
    fun getHandleNetwork(context: Context) : HandleNetwork {
        return HandleNetwork(context)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: HandleNetwork): OkHttpClient {
        val httpClient = OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).addInterceptor(interceptor).build()
        return httpClient
    }

    @Singleton
    @Provides
    fun getRetrofit(client : OkHttpClient): Retrofit {
        return Retrofit.Builder().client(client).baseUrl(ApplicationModule.BASE_URL).addCallAdapterFactory(
            RxJava2CallAdapterFactory.create()
        ).addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun getService(retrofit: Retrofit): DataService {
        return retrofit.create(DataService::class.java)
    }
}