package com.foton.paisesdadosapp.model

import com.google.gson.annotations.SerializedName

data class Pais(
    @SerializedName("PAICODIGO")
    val codigoPais: String,
    @SerializedName("PAINOME")
    val nomePais: String
)