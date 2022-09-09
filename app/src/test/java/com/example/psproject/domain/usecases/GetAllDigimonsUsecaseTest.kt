package com.example.psproject.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.psproject.data.repository.DataRepositoryImpl
import com.example.psproject.utils.Resource
import getDigimonsList
import getSuccessResourceDetailDigimon
import getSuccessResourceOfGetAllDigimons
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetAllDigimonsUsecaseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var repository: DataRepositoryImpl

    private lateinit var getAllDigimons: GetAllDigimonsUsecases

    @Before
    fun setUp() {
        getAllDigimons = GetAllDigimonsUsecases(
            repository
        )
    }

    @Test
    fun getAllDigimonsUseCaseTest() {
        runTest {
            coEvery { repository.getAllDigimonsRepository() } returns getSuccessResourceOfGetAllDigimons()
     val digimons = getAllDigimons()
            Assert.assertTrue(digimons is Resource.Success)
            Assert.assertEquals(digimons.data, getDigimonsList())
        }
    }
}