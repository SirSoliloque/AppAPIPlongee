package com.example.apiexample.api

import java.util.Date

data class PlongeeModel(
    val id: Int,
    var bateau: BateauModel,
    var date: Date,
    var moment: Int,
    var minplongeurs: Int,
    var maxplongeurs: Int,
    var niveau: Int,
    var active: Int,
    var etat: Int,
    var pilote: Int,
    var securite: Int,
    var directeur: Int,
)
