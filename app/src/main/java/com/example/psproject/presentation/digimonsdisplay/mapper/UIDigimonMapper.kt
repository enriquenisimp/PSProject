package com.example.psproject.presentation.digimonsdisplay.mapper

import com.example.psproject.data.model.DigimonModel
import com.example.psproject.presentation.digimonsdisplay.uimodel.DigimonUIModel


fun DigimonModel.toUIDigimon(): DigimonUIModel {
    return DigimonUIModel(
        this.name,
        this.level,
        this.img,
    )
}

fun List<DigimonModel>.toListOfUIDigimons(): List<DigimonUIModel> {
    return this.map {
        it.toUIDigimon()
    }
}