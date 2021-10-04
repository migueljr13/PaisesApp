package com.foton.paisesapp.repositories

import kotlinx.coroutines.flow.Flow
import com.foton.paisesdadosapp.model.Pais

interface PaisRepository {
    suspend fun listaPaises() : Flow<List<Pais>>
}
