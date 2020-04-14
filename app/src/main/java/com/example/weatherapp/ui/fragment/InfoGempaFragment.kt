package com.example.weatherapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.App

import com.example.weatherapp.R
import com.example.weatherapp.di.DaggerViewModelComponent
import com.example.weatherapp.di.ViewModelComponent
import com.example.weatherapp.model.Infogempa
import com.example.weatherapp.viewmodel.BmkgViewModel
import kotlinx.android.synthetic.main.fragment_info_gempa.*
import javax.inject.Inject
import javax.inject.Named

/**
 * A simple [Fragment] subclass.
 */
class InfoGempaFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel : BmkgViewModel

    private lateinit var infoGempa : Infogempa

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_gempa, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        DaggerViewModelComponent.builder().appComponent(App.app.getApplicationComponent()).build().inject(this)
        viewModel = ViewModelProvider(this,viewModelFactory).get(BmkgViewModel::class.java)

        viewModel.getGempa().observe(this.viewLifecycleOwner, Observer<Infogempa>() {
            infoGempa = it
            initUI()
        } )

        viewModel.getError().observe(this.viewLifecycleOwner, Observer<Throwable>() {
            Toast.makeText(this.context,it.message,Toast.LENGTH_LONG).show()
        })

    }


    fun initUI() {
        tanggal.text = infoGempa.gempa.tanggal
        jam.text = infoGempa.gempa.jam
        magnitude.text = infoGempa.gempa.magnitude
    }

}
