package com.example.apiexample.api

data class PalanqueModel(
val id : Int,
var plongee: Int,
var max_profondeur: Int? = null,
var max_duree: Int? = null,
var heure_immersion: String? = null,
var heure_sortie: String? = null,
var profondeur_realisee: Int? = null,
var duree_realisee: Int? = null
)
