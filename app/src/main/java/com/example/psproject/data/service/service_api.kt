package com.example.psproject.data.service

import com.example.psproject.data.model.DigimonModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceApi {
    @GET("/api/digimon")
    suspend fun getDigimonsApi() : Response<List<DigimonModel>?>

    @GET("/api/digimon/name/{name}")
    suspend fun getDigimonDetailApi(@Path("name") name:String) : Response<List<DigimonModel>?>
}