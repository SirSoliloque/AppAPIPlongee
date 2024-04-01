package com.example.apiexample.api

data class LieuModel(
    val id : Int = 0,
    var libelle : String? = "",
    var description : String? ="",
    var actif : Boolean? = false
)
