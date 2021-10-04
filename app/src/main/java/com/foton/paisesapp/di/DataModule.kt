package com.foton.paisesapp.di

import com.foton.paisesapp.repositories.PaisRepository
import com.foton.paisesapp.repositories.PaisRepositoryImpl
import com.foton.paisesapp.services.PaisesService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {

    fun load() = loadKoinModules(networkModules() + repositoriesModule())

    private fun networkModules(): Module {
        return module {
            single {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }
            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }
            single {
                createService<PaisesService>(get(), get())
            }
        }
    }

    private fun repositoriesModule(): Module {
        return module {
            single<PaisRepository> { PaisRepositoryImpl(get()) }
        }
    }

    private inline fun <reified T> createService(
        client: OkHttpClient,
        factory: GsonConverterFactory
    ): T {
        return Retrofit.Builder()
            .baseUrl("http://www.ipeadata.gov.br/api/odata4/")
            .client(client)
            .addConverterFactory(factory)
            .build().create(T::class.java)
    }
}