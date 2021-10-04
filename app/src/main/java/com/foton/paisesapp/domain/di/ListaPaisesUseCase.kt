package com.foton.paisesapp.domain.di

import com.foton.paisesapp.repositories.PaisRepository
import com.foton.paisesapp.core.UseCase
import kotlinx.coroutines.flow.Flow
import com.foton.paisesdadosapp.model.Pais

class ListaPaisesUseCase(private val repository: PaisRepository) : UseCase.NoParam<List<Pais>>() {

    override suspend fun execute(): Flow<List<Pais>> {
        return repository.listaPaises()
    }
}