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
    var listData = mutableListOf<Data>()
    val listDataOfMutable = MutableLiveData<List<Data>>()
    val errorMessage = MutableLiveData<Throwable>()
    var listDataFilter = mutableListOf<Data>()
    val compositeDisposable = CompositeDisposable()

    init {
        getAllData()
        Log.i("destroy","init repo")

    }

    fun getAllData() {
        Log.i("destroy","get all data")
        compositeDisposable.add(dataService.getData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap {
            Observable.fromIterable(it)
        }.subscribeWith(object: DisposableObserver<DataResponse>(){
            override fun onComplete() {
                listDataOfMutable.postValue(listData)
            }


            override fun onError(e: Throwable) {
                errorMessage.postValue(e)

            }

            override fun onNext(t: DataResponse) {
                listData.add(t.data)

            }

        }))
    }

    fun get() : MutableLiveData<List<Data>> {
        return listDataOfMutable
    }

    fun filter(query : CharSequence) {
        listDataFilter = listData
        listDataFilter = listDataFilter.filter { it.provinsi.contains(query,true )}.toMutableList()
        listDataOfMutable.postValue(listDataFilter)
    }

    fun clear() {
        compositeDisposable.clear()
    }
}