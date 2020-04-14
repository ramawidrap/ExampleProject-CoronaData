package com.example.weatherapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.Data
import com.example.weatherapp.network.repository.DataRepository
import javax.inject.Inject

class DataViewModel @Inject constructor(val repo : DataRepository) : ViewModel() {



    fun getData() : LiveData<List<Data>> {
        Log.i("destroy","live data")
        return repo.get()
    }

    fun getError() : LiveData<Throwable> {
        return repo.errorMessage
    }

    fun clear() {
        repo.clear()
    }

    fun filter(query : CharSequence) {
        repo.filter(query)
    }

}