package com.tapmobile.tapyoutube.core.network

class LoadingState private constructor(val status: Status) {

    companion object {

        fun completed(): LoadingState {
            return LoadingState(Status.COMPLETED)
        }

        fun loading(): LoadingState {
            return LoadingState(Status.LOADING)
        }

    }

    enum class Status {
        LOADING,
        COMPLETED
    }

}
