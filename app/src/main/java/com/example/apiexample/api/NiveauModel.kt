package com.example.apiexample.api

data class NiveauModel(val id: Int,
                       var libelle: String? = "",
                       var code: String? ="",
                       var profondeur_si_encadre: Int? = null,
                       var profondeur_si_autonome: Int? = null,
                       var niveau: Int? = null,
                       var guide_de_palanquee: Boolean? = false,
                       var directeur_de_plongee: Boolean? = false)
