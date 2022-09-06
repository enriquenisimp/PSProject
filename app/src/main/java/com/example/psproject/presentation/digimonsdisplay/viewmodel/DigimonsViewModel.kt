package com.example.psproject.presentation.digimonsdisplay.viewmodel

import androidx.lifecycle.*
import com.example.psproject.data.model.DigimonModel
import com.example.psproject.domain.usecases.Usecases
import kotlinx.coroutines.*
import com.example.psproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DigimonsViewmodel @Inject constructor(
  private val usecases: Usecases,
   private val dispatcher: CoroutineDispatcher) : ViewModel() {

   private val _digimons:MutableLiveData<Resource<List<DigimonModel>>> = MutableLiveData()

   val digimons:LiveData<Resource<List<DigimonModel>>> get() =_digimons


    fun getAllDigimons() =
        viewModelScope.launch(dispatcher){
            _digimons.postValue(Resource.Loading())

            val result =usecases.getAllDigimonsUseCase()

            try{

            if(result is Resource.Success){
                result.data?.let {
                    _digimons.postValue(Resource.Success(data = it))
                }
            }}catch (e:Exception){
                _digimons.postValue(Resource.Error(message = e.message?: "Error detected!"))
            }
        }
}