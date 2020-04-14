package com.example.weatherapp.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name= "gempa",strict = false)
data class Gempa (
    @field:Element(name = "Tanggal")
    @param:Element(name = "Tanggal") var tanggal : String,

    @field:Element(name = "Jam")
    @param:Element(name = "Jam")var jam : String,

    @field:Element(name = "Magnitude")
    @param:Element(name = "Magnitude")var magnitude : String


)

@Root(name= "Infogempa", strict = false)
data class Infogempa (

    @field:Element(name = "gempa")
    @param:Element(name = "gempa") var gempa : Gempa
)