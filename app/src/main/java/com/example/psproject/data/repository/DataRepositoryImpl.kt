package com.example.psproject.data.repository

import com.example.psproject.data.model.DigimonModel
import com.example.psproject.data.service.ServiceApi
import com.example.psproject.utils.Resource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class DataRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi) :
    DataRepository, SafetyCall() {

    override suspend fun getAllDigimonsRepository():Resource<List<DigimonModel>?>{
          return safeApiCall{
               serviceApi.getDigimonsApi()
            }
    }

    override suspend fun getDigimonDetailRepository(name: String): Resource<List<DigimonModel>?> {
        return safeApiCall{
            serviceApi.getDigimonDetailApi(name)
        }
    }
}