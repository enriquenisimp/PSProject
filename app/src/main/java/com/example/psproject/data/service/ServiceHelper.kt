package com.example.psproject.data.service


import javax.inject.Inject

class ServiceHelper @Inject constructor(private val serviceApi: ServiceApi) {
    suspend fun getDigimonsService() =
        serviceApi.getDigimonsApi()
}
