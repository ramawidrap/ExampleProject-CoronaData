package com.example.weatherapp.network

import java.io.IOException

class NoInternetException : IOException() {
    override val message: String?
        get() = "No network available, please check your connection"
}