package com.example.psproject.data.repository

import androidx.lifecycle.LiveData
import com.example.psproject.data.model.DigimonModel

interface DataRepository {
    suspend fun getDigimons(): Pair<LiveData<List<DigimonModel>?>, Boolean>
}