package com.foton.paisesapp

import android.app.Application
import com.foton.paisesapp.di.DataModule
import com.foton.paisesapp.presentation.di.PresentationModule
import com.foton.paisesdadosapp.domain.di.DomainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}