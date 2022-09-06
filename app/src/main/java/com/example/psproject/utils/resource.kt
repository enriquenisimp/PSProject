package com.example.psproject.utils

sealed class Resource<T>(val status: Status, val data: T?, val message: String?) {
        class Success<T>(data: T): Resource<T>(status = Status.SUCCESS, data = data, message = null)
        class Error<T>(message: String): Resource<T>(status = Status.ERROR, data = null, message = message)
        class Loading<T>(): Resource<T>(status = Status.LOADING, data = null, message = null)
}