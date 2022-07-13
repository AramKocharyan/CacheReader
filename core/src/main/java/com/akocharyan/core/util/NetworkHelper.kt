package com.akocharyan.core.util

import android.util.Log
import com.akocharyan.core.util.network.Error
import com.akocharyan.core.util.network.State
import retrofit2.Call

const val BASE_URL = "https://jbwzx.github.io/MTT/"

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
