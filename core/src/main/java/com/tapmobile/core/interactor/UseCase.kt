package com.tapmobile.core.interactor

import com.tapmobile.core.network.Error
import com.tapmobile.core.network.State
import kotlinx.coroutines.*

interface UseCase<out Type, in Params>  {

    suspend fun run(params: Params): State<Error, Type>

    operator fun invoke(
        params: Params,
        scope: CoroutineScope,
        onResult: State<Error, Type>.() -> Unit
    ) {
        scope.launch(Dispatchers.Main) {
            val deferred = async(Dispatchers.IO) {
                run(params)
            }
            onResult(deferred.await())
        }
    }

}
