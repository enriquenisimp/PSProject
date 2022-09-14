package com.example.psproject.presentation.digimonsdisplay.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psproject.domain.usecases.GetDetailDigimonUseCase
import com.example.psproject.presentation.digimonsdisplay.mapper.toListOfUIDigimons
import com.example.psproject.presentation.digimonsdisplay.uimodel.DigimonUIModel
import com.example.psproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DigimonDetailViewModel @Inject constructor(
    private val getDetailDigimonUseCase: GetDetailDigimonUseCase,
    private val dispatcher: CoroutineDispatcher): ViewModel() {

    private val _digimon: MutableLiveData<Resource<List<DigimonUIModel>?>> = MutableLiveData()

    val digimon: LiveData<Resource<List<DigimonUIModel>?>> get() =_digimon

    fun getDigimon(name:String) =
        viewModelScope.launch(dispatcher){
            _digimon.postValue(Resource.Loading())

            val result =getDetailDigimonUseCase(name)

            try{
                if(result is Resource.Success){
                    result.data?.let {

                        val uiDigimon = it.toListOfUIDigimons()
                        _digimon.postValue(Resource.Success(data = uiDigimon))
                    }
                }}catch (e:Exception){
                Log.d("detail", e.message.toString())
                _digimon.postValue(Resource.Error(message = e.message?: "Error detected!"))
            }
        }

}