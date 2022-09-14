package com.example.psproject.data.repository

import com.example.psproject.data.model.DigimonModel
import com.example.psproject.utils.Resource

interface  DataRepository {
    suspend fun getAllDigimonsRepository():Resource<List<DigimonModel>?>
    suspend fun getDigimonDetailRepository(name:String):Resource<List<DigimonModel>?>
}