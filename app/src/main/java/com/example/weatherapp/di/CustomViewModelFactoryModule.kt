package com.example.weatherapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.viewmodel.*
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Named
import kotlin.reflect.KClass

@Module
abstract class CustomViewModelFactoryModule {




    @Binds
    abstract fun getFactory(factory : AppViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DataViewModel::class)
    abstract  fun bindDataViewModel(dataViewModel : DataViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BmkgViewModel::class)
    abstract  fun bindBmkgViewModel(bmkgViewModel : BmkgViewModel) : ViewModel


}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value : KClass<out ViewModel>)