package com.foton.paisesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.foton.paisesapp.R
import com.foton.paisesapp.core.createDialog
import com.foton.paisesapp.databinding.ActivityMainBinding
import com.foton.paisesapp.presentation.di.MainViewModel
import com.foton.paisesapp.core.createProgressDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val dialog by lazy { createProgressDialog() }
    private val viewModel by viewModel<MainViewModel>()
    private val adapter by lazy { PaisListaAdapter() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.rvPaises.adapter = adapter
        bindObserver()
        lifecycle.addObserver(viewModel)
    }

    private fun bindObserver() {
        viewModel.paises.observe(this) {
            when (it) {
                MainViewModel.State.Loading -> dialog.show()
                is MainViewModel.State.Error -> {
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                    dialog.dismiss()
                }
                is MainViewModel.State.Success -> {
                    dialog.dismiss()
                    adapter.submitList(it.list)
                }
            }
        }
    }
}