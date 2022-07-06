package com.akocharyan.core.interactor

import com.akocharyan.core.network.Error
import com.akocharyan.core.network.State
import kotlinx.coroutines.*

interface UseCase<out Type, in Params>  {

    suspend fun run(params: Params): State<Error, Type>

    operator fun invoke(scope: CoroutineScope, params: Params, onResult: State<Error, Type>.() -> Unit) {
        val job = scope.async(Dispatchers.IO) { run(params) }
        scope.launch(Dispatchers.Main) { onResult(job.await()) }
    }

    object None
}
