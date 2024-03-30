package com.example.apiexample.api

data class PalanqueModel(
val id : Int,
var plongee: Int,
var max_profondeur: Int,
var max_duree: Int,
var heure_immersion: String? = null,
var heure_sortie: String? = null,
var profondeur_realisee: Int? = null,
var duree_realisee: Int? = null
) {
    init {
        require(profondeur_realisee == null || profondeur_realisee!! <= max_profondeur) {
            "La profondeur réalisée ($profondeur_realisee) ne peut pas dépasser la profondeur maximale ($max_profondeur)."
        }
        require(duree_realisee == null || duree_realisee!! <= max_duree) {
            "La durée réalisée ($duree_realisee) ne peut pas dépasser la durée maximale ($max_duree)."
        }
    }
}
