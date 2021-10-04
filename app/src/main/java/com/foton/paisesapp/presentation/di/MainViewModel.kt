package com.foton.paisesapp.presentation.di

import androidx.lifecycle.*
import com.foton.paisesapp.domain.di.ListaPaisesUseCase
import com.foton.paisesdadosapp.model.Pais
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(private val listaPaisesUseCase: ListaPaisesUseCase) :
    ViewModel(), LifecycleObserver {

    private val _paises = MutableLiveData<State>()
    val paises: LiveData<State> = _paises

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun getPaises() {
        viewModelScope.launch {
            listaPaisesUseCase()
                .flowOn(Dispatchers.Main)
                .onStart {
                    _paises.value = State.Loading
                }
                .catch {
                    _paises.value = State.Error(it)
                }.collect {
                    _paises.value = State.Success(it)
                }
        }
    }

    sealed class State {
        object Loading : State()
        data class Success(val list: List<Pais>) : State()
        data class Error(val error: Throwable) : State()
    }
}