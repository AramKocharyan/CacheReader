package com.akocharyan.core.interactor

import com.akocharyan.core.models.Error
import com.akocharyan.core.models.State
import kotlinx.coroutines.*

interface UseCase<out Type, in Params> {

    suspend fun run(params: Params): State<Error, Type>

    operator fun invoke(
        scope: CoroutineScope,
        params: Params,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        onResult: State<Error, Type>.() -> Unit = {}
    ) {
        val job = scope.async(dispatcher) { run(params) }
        scope.launch(Dispatchers.Main) { onResult(job.await()) }
    }

    object None
}
