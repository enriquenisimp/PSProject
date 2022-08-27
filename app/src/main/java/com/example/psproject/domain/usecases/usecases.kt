package com.example.psproject.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.psproject.data.model.DigimonModel
import com.example.psproject.data.repository.DataRepository

class Usecases(val dataRepository: DataRepository) {
    suspend fun loadDigimonScreenData()
    :Pair<LiveData<List<DigimonModel>?>, Boolean> {

       return dataRepository.getDigimons()
    }
}