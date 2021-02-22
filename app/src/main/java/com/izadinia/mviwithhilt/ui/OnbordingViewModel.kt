package com.izadinia.mviwithhilt.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.izadinia.mviwithhilt.model.LoginResponse
import com.izadinia.mviwithhilt.repository.MainRepo
import com.izadinia.mviwithhilt.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class OnbordingViewModel @ViewModelInject constructor(
    private val mainRepo: MainRepo
) : ViewModel() {
    private val _dataState: MutableLiveData<DataState<LoginResponse>> = MutableLiveData()
    val dataState: LiveData<DataState<LoginResponse>>
        get() = _dataState

    @ExperimentalCoroutinesApi
    fun login(email:String,password:String) {
        viewModelScope.launch {
                    mainRepo.login(email,password )
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }.launchIn(viewModelScope)

        }
    }

}
