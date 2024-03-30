package com.example.apiexample.api

data class AdherentModel(
    val id: Int,
    var nom: String,
    var prenom: String,
    var email: String,
    var actif: Boolean,
    var license: String,
    var dateCertificatMedical: String,
    var forfait: String,
    var niveau: Int
)
