package com.example.psproject.domain.usecases

import com.example.psproject.data.model.DigimonModel
import com.example.psproject.data.repository.DataRepositoryImpl
import com.example.psproject.utils.Resource
import javax.inject.Inject

class Usecases @Inject constructor(private val dataRepository: DataRepositoryImpl) {
    suspend fun getAllDigimonsUseCase()
    :Resource<List<DigimonModel>?> {
       return dataRepository.getAllDigimonsRepository()
    }

    suspend fun getDigimonDetailUseCase(name:String)
            :Resource<List<DigimonModel?>> {
        return dataRepository.getDigimonRepository(name)
    }
}