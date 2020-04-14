package com.example.weatherapp.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import com.example.weatherapp.ui.fragment.ListRegionFragment
import com.example.weatherapp.R
import com.example.weatherapp.adapter.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var pagerAdapter: PagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar2)

        pagerAdapter = PagerAdapter(supportFragmentManager)
        pagerAdapterView.adapter = pagerAdapter


    }


}
