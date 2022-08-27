package com.example.psproject.data.service

import androidx.lifecycle.MutableLiveData
import com.example.psproject.data.model.DigimonModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {
    @GET("/api/digimon")
    suspend fun getDigimons() : Response<List<DigimonModel>?>
}