package com.example.psproject.presentation.digimonsdisplay.mapper

import com.example.psproject.data.model.DigimonModel
import com.example.psproject.presentation.digimonsdisplay.uimodel.UiDigimonModel


fun DigimonModel.toUIDigimon(): UiDigimonModel {
        return UiDigimonModel(
            this.name,
            this.level,
            this.img,
        )
}

fun List<DigimonModel>.toListOfUIDigimons(): List<UiDigimonModel> {
    return this.map {
        it.toUIDigimon()
    }
}