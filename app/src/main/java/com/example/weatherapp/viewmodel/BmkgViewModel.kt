package com.example.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.Infogempa
import com.example.weatherapp.network.repository.GempaRepository
import javax.inject.Inject

class BmkgViewModel @Inject constructor(val gempaRepository: GempaRepository) : ViewModel() {

    fun getGempa() : LiveData<Infogempa> {
        return gempaRepository.infoGempa
    }

    fun getError() : LiveData<Throwable> {
        return gempaRepository.error
    }

    fun clear() {
        return gempaRepository.clear()
    }
}