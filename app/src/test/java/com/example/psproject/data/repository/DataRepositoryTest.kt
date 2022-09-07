package com.example.psproject.data.repository

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule


import com.example.psproject.data.service.ServiceApi
import getSuccessResponseDetailDigimon
import getSuccessResponseOfGetAllDigimons
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
 class DataRepositoryImplTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

     lateinit var repository: DataRepositoryImpl

    @RelaxedMockK
    private lateinit var serviceApi: ServiceApi

    @Before
    fun setUp() {
        repository = DataRepositoryImpl(serviceApi)
    }


    @Test
    fun getAllDigimonsFromBackend() {
        runTest {
            coEvery { serviceApi.getDigimonsApi() } returns getSuccessResponseOfGetAllDigimons()
            val list = repository.getAllDigimonsRepository()
            assertEquals(
                list.data,
                getSuccessResponseOfGetAllDigimons().body()
            )
        }
    }

    @Test
    fun getDetailDigimonFromBackend() {
        runTest {
            coEvery { serviceApi.getDigimonDetailApi("agumon") } returns getSuccessResponseDetailDigimon("agumon")
            val list = repository.getDigimonRepository("agumon")
            assertEquals(
                list.data,
                getSuccessResponseDetailDigimon("agumon").body()
            )
        }
    }
}