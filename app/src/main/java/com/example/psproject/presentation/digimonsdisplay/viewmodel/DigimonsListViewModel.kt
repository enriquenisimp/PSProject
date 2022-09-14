package com.example.psproject.presentation.digimonsdisplay.viewmodel

import androidx.lifecycle.*
import com.example.psproject.domain.usecases.GetAllDigimonsUsecase
import com.example.psproject.presentation.digimonsdisplay.mapper.toListOfUIDigimons
import com.example.psproject.presentation.digimonsdisplay.uimodel.DigimonUIModel
import kotlinx.coroutines.*
import com.example.psproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DigimonsListViewModel @Inject constructor(
    private val getAllDigimonsUsecase: GetAllDigimonsUsecase,
    private val dispatcher: CoroutineDispatcher
    ) : ViewModel() {

    private val _digimons:MutableLiveData<Resource<List<DigimonUIModel>>> = MutableLiveData()

    val digimons:LiveData<Resource<List<DigimonUIModel>>> get() =_digimons


    fun getAllDigimons() {
        viewModelScope.launch(dispatcher) {
            _digimons.postValue(Resource.Loading())

            val result = getAllDigimonsUsecase()

            if (result is Resource.Success) {
                result.data?.let {
                    val uiDigimon = it.toListOfUIDigimons()
                    _digimons.postValue(Resource.Success(data = uiDigimon))
                }
            } else if (result is Resource.Error) {
                _digimons.postValue(Resource.Error(message = result.message!!))
            }
        }
    }
}