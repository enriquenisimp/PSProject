package com.example.psproject.presentation.digimons_display.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.psproject.domain.usecases.Usecases

class MyViewModelFactory constructor(private val usecases: Usecases) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DigimonsViewmodel::class.java)) {
            DigimonsViewmodel(this.usecases) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}