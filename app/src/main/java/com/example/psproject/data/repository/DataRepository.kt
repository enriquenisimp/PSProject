package com.example.psproject.data.repository

import androidx.lifecycle.LiveData
import com.example.psproject.data.model.DigimonModel
import com.example.psproject.utils.Resource
import retrofit2.Response

abstract class DataRepository {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Resource<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return Resource.Success(body)
                }
            }
            return Resource.Error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return Resource.Error(e.message ?: e.toString())
        }
    }
    private fun <T> error(errorMessage: String): Resource<T> =
        Resource.Error("Api call failed $errorMessage")
}