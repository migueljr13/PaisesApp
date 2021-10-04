package com.foton.paisesapp.repositories

import com.foton.paisesapp.services.PaisesService
import com.foton.paisesapp.core.RemoteException
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class PaisRepositoryImpl(private val service: PaisesService) : PaisRepository {

    override suspend fun listaPaises() = flow {

        try {
            val listaPais = service.listaPaises()
            emit(listaPais)
        } catch (ex: HttpException) {
            throw RemoteException(ex.message() ?: "Não é possível fazer a busca no momento")
        }
    }
}