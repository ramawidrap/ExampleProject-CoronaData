package com.example.weatherapp.network.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.model.Infogempa
import com.example.weatherapp.network.service.BmkgService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GempaRepository @Inject constructor(val service: BmkgService) {

    val infoGempa = MutableLiveData<Infogempa>()
    val error = MutableLiveData<Throwable>()
    val compositeDisposable = CompositeDisposable()


    init {
        getGempa()
    }

    fun getGempa() {
        compositeDisposable.add(service.getInfoGempa().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(object : DisposableObserver<Infogempa>(){
            override fun onComplete() {
            }

            override fun onNext(gempa: Infogempa) {
                infoGempa.postValue(gempa)
            }

            override fun onError(errorThrow: Throwable) {
                Log.i("Gempa",errorThrow.message)
                error.postValue(errorThrow)
            }

        }))
    }

    fun clear() {
        compositeDisposable.clear()
    }
}