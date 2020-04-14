package com.example.weatherapp.di

import android.content.Context
import android.util.Log
import com.example.weatherapp.network.HandleNetwork
import com.example.weatherapp.network.service.BmkgService
import com.example.weatherapp.network.service.DataService
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    companion object {
        const val BASE_URL_CORONA = "https://api.kawalcorona.com/"
        const val BASE_URL_BMKG = "https://data.bmkg.go.id/"
    }


    @Singleton
    @Provides
    fun getHandleNetwork (context: Context) : HandleNetwork {
        Log.i("destroy"," punya handlenetwork ${context.hashCode().toString()}")
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
    @Named("JSON")
    fun getRetrofit(client : OkHttpClient): Retrofit {
        return Retrofit.Builder().client(client).baseUrl(BASE_URL_CORONA).addCallAdapterFactory(
            RxJava2CallAdapterFactory.create()
        ).addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    @Named("XML")
    fun getXmlRetrofit(client : OkHttpClient) : Retrofit {
        return Retrofit.Builder().client(client).baseUrl(BASE_URL_BMKG).addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(
            Schedulers.io())).addConverterFactory(SimpleXmlConverterFactory.createNonStrict(Persister(AnnotationStrategy()))).build()
    }

    @Singleton
    @Provides
    fun getService (@Named("JSON") retrofit: Retrofit): DataService {
        return retrofit.create(DataService::class.java)
    }

    @Singleton
    @Provides
    fun getXmlService(@Named("XML") retrofit: Retrofit) : BmkgService {
        return retrofit.create(BmkgService::class.java)
    }
}