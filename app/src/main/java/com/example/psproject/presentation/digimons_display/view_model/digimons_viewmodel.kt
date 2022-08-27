package com.example.psproject.presentation.digimons_display.view_model

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.psproject.data.model.DigimonModel
import com.example.psproject.domain.usecases.Usecases
import kotlinx.coroutines.*
import kotlin.system.*
import androidx.lifecycle.liveData
import com.example.psproject.utils.Resource

import kotlinx.coroutines.Dispatchers

class DigimonsViewmodel(val usecases: Usecases) : ViewModel() {
    fun getDigimons() =  liveData(Dispatchers.IO) {
        Log.d("tag", "entro model")
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(usecases.loadDigimonScreenData().first.value))
            } catch (exception: Exception){
                emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!" ))
            }
        }
}