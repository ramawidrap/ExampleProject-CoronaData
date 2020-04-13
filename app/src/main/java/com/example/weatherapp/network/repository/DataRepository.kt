package com.example.weatherapp.network.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.model.Data
import com.example.weatherapp.network.NoInternetException
import com.example.weatherapp.network.response.DataResponse
import com.example.weatherapp.network.service.DataService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class DataRepository @Inject constructor(val dataService: DataService) {
    val listData = mutableListOf<Data>()
    val listDataOfMutable = MutableLiveData<List<Data>>()
    val errorMessage = MutableLiveData<Throwable>()

    val compositeDisposable = CompositeDisposable()

    init {
        getAllData()
    }

    fun getAllData() {

        compositeDisposable.add(dataService.getData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap {
            Observable.fromIterable(it)
        }.subscribeWith(object: DisposableObserver<DataResponse>(){
            override fun onComplete() {
                listDataOfMutable.postValue(listData)
                Log.i("getData","Complete")
            }


            override fun onError(e: Throwable) {
                errorMessage.postValue(e)
                Log.i("getData","${e.message}")

            }

            override fun onNext(t: DataResponse) {
                listData.add(t.data)
                Log.i("getData","Recieve Data")

            }

        }))
    }

    fun get() : MutableLiveData<List<Data>> {
        return listDataOfMutable
    }

    fun clear() {
        compositeDisposable.clear()
    }
}