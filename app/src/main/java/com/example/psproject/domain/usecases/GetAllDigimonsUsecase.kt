package com.example.psproject.domain.usecases

import com.example.psproject.data.model.DigimonModel
import com.example.psproject.data.repository.DataRepositoryImpl
import com.example.psproject.utils.Resource
import javax.inject.Inject

class GetAllDigimonsUsecase @Inject constructor(private val dataRepository: DataRepositoryImpl) {
    suspend operator fun invoke()
    :Resource<List<DigimonModel>?> = dataRepository.getAllDigimonsRepository()


}