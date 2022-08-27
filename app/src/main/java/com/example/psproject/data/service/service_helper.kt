package com.example.psproject.data.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceHelper {
    val baseUrl = "https://digimon-api.vercel.app";

    fun getInstance():Retrofit{
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    }
}