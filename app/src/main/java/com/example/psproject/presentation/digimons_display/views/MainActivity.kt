package com.example.psproject.presentation.digimons_display.views

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.psproject.R
import com.example.psproject.data.model.DigimonModel
import com.example.psproject.data.repository.DataRepository
import com.example.psproject.data.repository.DataRepositoryImpl
import com.example.psproject.data.service.ServiceHelper
import com.example.psproject.databinding.ActivityMainBinding
import com.example.psproject.domain.usecases.Usecases
import com.example.psproject.presentation.digimons_display.DigimonAdapter
import com.example.psproject.presentation.digimons_display.view_model.DigimonsViewmodel
import com.example.psproject.presentation.digimons_display.view_model.MyViewModelFactory
import com.example.psproject.utils.Status
import com.example.psproject.utils.Status.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var digimonViewModel : DigimonsViewmodel
    private lateinit var digimonAdapter: DigimonAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setUpUI()
        setUpObservers()

    }

    private fun setupViewModel() {
        digimonViewModel =
            ViewModelProvider(this, MyViewModelFactory(Usecases(DataRepositoryImpl(ServiceHelper))))[DigimonsViewmodel::class.java]
    }

    private fun setUpUI(){
        binding.recyclerView.layoutManager =LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                ( binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        digimonAdapter = DigimonAdapter(arrayListOf())
        binding.recyclerView.adapter = digimonAdapter
    }

    private fun setUpObservers(){
        digimonViewModel.getDigimons().observe(this, Observer{
            it?.let {
                resource ->
                when(resource.status){
                    SUCCESS -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let{
                            digimons->retrieveList(digimons)}
                    }
                    ERROR -> {
                        binding.recyclerView.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        Log.d("Activity", it.message?:"Error ocurried")

                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    LOADING -> {
                        binding.recyclerView.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun retrieveList(digimons: List<DigimonModel>) {
        digimonAdapter.apply {
            addDigimonsToList(digimons)
            notifyDataSetChanged()
        }
    }
}