package com.example.psproject.data.service

import com.example.psproject.utils.Resource
import retrofit2.Response
import javax.inject.Inject

class ServiceHelper @Inject constructor(private val serviceApi: ServiceApi) {
    suspend fun getDigimonsService() =
        serviceApi.getDigimonsApi()
}
