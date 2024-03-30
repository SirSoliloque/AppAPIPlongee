package com.example.apiexample.api

data class NiveauModel(val id: Int,
                       val libelle: String,
                       val code: String,
                       val profondeur_si_encadre: Int,
                       val profondeur_si_autonome: Int,
                       val niveau: Int,
                       val guide_de_palanquee: Boolean,
                       val directeur_de_plongee: Boolean)
