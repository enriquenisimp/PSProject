package com.example.psproject.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.psproject.data.model.DigimonModel
import com.example.psproject.data.service.ServiceApi
import com.example.psproject.data.service.ServiceHelper

class DataRepositoryImpl(serviceHelper: ServiceHelper) : DataRepository {
   val service = serviceHelper.getInstance().create(ServiceApi::class.java)

    override suspend fun getDigimons():Pair<LiveData<List<DigimonModel>?>, Boolean>{


        //Here we should check if we want to use data from database,
        // cache or directly from Api, then only make totally sense to separe usecases from repository
        // in this example, but as we are here to shown how i would structure the project
        // i will do it anyway
       val digimonsDummy = MutableLiveData<List<DigimonModel>?>()
        var isSuccess = false
            // launching a new coroutine
       val result = service.getDigimons()
        //Checking the result
        Log.d("digimons:",result.body().toString())

        if(result.isSuccessful)
        {
            isSuccess = true
            digimonsDummy.postValue(result.body())
        }else{
            isSuccess = false
        }

        return digimonsDummy to isSuccess;
    }
}