package com.example.weatherapp.network.response

import com.example.weatherapp.model.Data
import com.google.gson.annotations.SerializedName

data class DataResponse
    (
    @SerializedName("attributes")
    val data: Data
)