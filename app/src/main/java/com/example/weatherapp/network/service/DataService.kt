package com.example.weatherapp.network.service

import com.example.weatherapp.network.response.DataResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface DataService {

    @GET("indonesia/provinsi")
    fun getData() : Observable<List<DataResponse>>
}