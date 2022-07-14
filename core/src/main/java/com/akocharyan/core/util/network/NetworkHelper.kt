package com.akocharyan.core.util.network

import android.util.Log
import com.akocharyan.core.models.Error
import com.akocharyan.core.models.State
import retrofit2.Call

inline fun <T, R> request(
    call: Call<T>,
    transform: (T) -> R,
    default: T,
    networkHandler: NetworkHandler,
): State<Error, R> {
    return try {
        if (networkHandler.isNetworkAvailable()) {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> State.Success(transform((response.body() ?: default)))
                false -> State.Failure(Error.ServerError)
            }
        } else {
            State.Failure(Error.NetworkConnection)
        }
    } catch (exception: Throwable) {
        Log.e("NetworkHelper", exception.message, exception) // TODO: integrate firebase
        State.Failure(Error.ServerError)
    }
}
