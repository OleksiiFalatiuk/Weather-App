package com.example.weatherapp.utils

data class Resource<out T>(val status: Status, val data: T?, val exception: Throwable?) {
    enum class Status {
        LOADING,
        SUCCESS,
        ERROR,
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(exception: Throwable): Resource<T> {
            return Resource(Status.ERROR, null, exception)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}