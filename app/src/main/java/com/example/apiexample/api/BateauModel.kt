package com.example.apiexample.api

data class BateauModel(
    val id: Int = 0,
    var libelle : String? = "",
    var max_personnes : Int? = null,
    var actif : Boolean? = false
    )
