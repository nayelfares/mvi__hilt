package com.izadinia.mviwithhilt.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.izadinia.mviwithhilt.model.Blog
import com.izadinia.mviwithhilt.repository.MainRepo
import com.izadinia.mviwithhilt.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val mainRepo: MainRepo,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _dataState: MutableLiveData<DataState<List<Blog>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<Blog>>>
        get() = _dataState

    @ExperimentalCoroutinesApi
    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetBlogEvent -> {
                    mainRepo.getBlogs()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }.launchIn(viewModelScope)
                }
                MainStateEvent.None -> {/*Do Nothing*/
                }
            }
        }
    }
}

sealed class MainStateEvent {
    object GetBlogEvent : MainStateEvent()

    object None : MainStateEvent()
}