package com.akocharyan.core.platorm

import androidx.lifecycle.ViewModel
import com.akocharyan.core.network.Error
import com.akocharyan.core.network.LoadingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {

    private val _failure: MutableStateFlow<Error> = MutableStateFlow(Error.None)
    val failure: StateFlow<Error> = _failure

    private val _loading = MutableStateFlow(LoadingState.completed())
    val loading: StateFlow<LoadingState> = _loading

    protected fun handleFailure(failure: Error) {
        _failure.value = failure
    }

    protected fun handleLoading(failure: LoadingState) {
        _loading.value = failure
    }

}
