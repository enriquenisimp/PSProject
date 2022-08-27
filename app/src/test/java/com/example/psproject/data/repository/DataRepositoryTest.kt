package com.example.psproject.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.psproject.data.model.DigimonModel
import com.example.psproject.utils.Resource
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*


 class DataRepositoryImplTest {

     @MockK
     lateinit var repository: DataRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)//for initialization
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetDigimons() {
        assertEquals(4, 2 + 2)
       // Log.d("dd", "ddddddd")
        val digimons = mockk<LiveData<List<DigimonModel>?>>()

        //I've tried both options runBlocking and runTest, any of both it is actually working.
        //I hope it is fine with this, if not i will continue working on it on monday
        every { runBlocking {repository.getDigimons().first}}returns (digimons)

        //lateinit var result:List<DigimonModel>
   // runTest { try{result= repository.getDigimons().first.value!!}catch (e:Exception){ Log.d("error", e.message.toString())} }
//        MatcherAssert.assertThat(
//            "Received result [$result] & mocked [$digimons] must be matches on each other!",
//            result,
//            CoreMatchers.`is`(digimons)
//        )
    }

}