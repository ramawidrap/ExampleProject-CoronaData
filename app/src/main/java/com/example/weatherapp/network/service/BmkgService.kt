package com.example.weatherapp.network.service

import com.example.weatherapp.model.Infogempa
import io.reactivex.Observable
import retrofit2.http.GET

interface BmkgService {

    @GET("autogempa.xml")
    fun getInfoGempa() : Observable<Infogempa>
}