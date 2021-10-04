package com.foton.paisesapp.services

import com.foton.paisesdadosapp.model.Pais
import retrofit2.http.GET

interface PaisesService {

    @GET("Paises")
    suspend fun listaPaises(): List<Pais>

}