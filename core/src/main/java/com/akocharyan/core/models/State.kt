package com.akocharyan.core.models

sealed interface State<out F, out S> {

    data class Success<out S>(val data: S) : State<Nothing, S>

    data class Failure(val error: Error) : State<Error, Nothing>

    fun fold(failure: (Error) -> Any, success: (S) -> Any): Any =
        when (this) {
            is Failure -> failure(error)
            is Success -> success(data)
        }

}
