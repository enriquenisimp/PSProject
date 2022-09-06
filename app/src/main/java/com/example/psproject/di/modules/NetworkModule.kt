package com.example.psproject.di.modules

import com.example.psproject.data.service.ServiceApi
import com.example.psproject.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ActivityRetainedComponent::class)
@Module
object NetworkModule {
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.baseUrl)
            .build()
    }

    @Provides
    internal fun provideServiceApi(retrofit: Retrofit):ServiceApi{
        return retrofit.create(ServiceApi::class.java)
    }
}