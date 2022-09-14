package com.example.psproject.di.modules

import com.example.psproject.data.model.DigimonModel
import com.example.psproject.data.repository.DataRepository
import com.example.psproject.data.repository.DataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
class ActivityModule {

    @Provides
    fun provideRepository(digimonRepositoryImpl: DataRepositoryImpl): DataRepository {
        return digimonRepositoryImpl
    }

    @Provides
    fun provideDigimonArrayList():ArrayList<DigimonModel>{
        return arrayListOf()
    }

}