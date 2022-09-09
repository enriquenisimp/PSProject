package com.example.psproject.presentation.digimonsdisplay.viewmodel

import androidx.lifecycle.*
import com.example.psproject.domain.usecases.GetAllDigimonsUsecases
import com.example.psproject.presentation.digimonsdisplay.mapper.toListOfUIDigimons
import com.example.psproject.presentation.digimonsdisplay.uimodel.UiDigimonModel
import kotlinx.coroutines.*
import com.example.psproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DigimonsViewmodel @Inject constructor(
  private val getAllDigimonsUsecases: GetAllDigimonsUsecases,
   private val dispatcher: CoroutineDispatcher) : ViewModel() {

   private val _digimons:MutableLiveData<Resource<List<UiDigimonModel>>> = MutableLiveData()

   val digimons:LiveData<Resource<List<UiDigimonModel>>> get() =_digimons


    fun getAllDigimons() =
        viewModelScope.launch(dispatcher){
            _digimons.postValue(Resource.Loading())

            val result =getAllDigimonsUsecases()

            try{
            if(result is Resource.Success){
                result.data?.let {
                    val uiDigimon = it.toListOfUIDigimons()
                    _digimons.postValue(Resource.Success(data = uiDigimon))
                }
            }}catch (e:Exception){
                _digimons.postValue(Resource.Error(message = e.message?: "Error detected!"))
            }
        }
}