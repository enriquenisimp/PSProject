package com.example.psproject.presentation.digimonsdisplay.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.psproject.databinding.FragmentDigimonDetailBinding
import com.example.psproject.presentation.digimonsdisplay.viewmodel.DigimonDetailViewModel
import com.example.psproject.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DigimonDetail : Fragment() {

    private lateinit var binding: FragmentDigimonDetailBinding
    private val args: DigimonDetailArgs by navArgs()
    private val digimonViewModel by viewModels<DigimonDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDigimonDetailBinding.inflate(inflater,container,false)
        digimonViewModel.getDigimon(args.digimonName)
        viewModelSetUp()

        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun viewModelSetUp(){
        digimonViewModel.digimon.observe(viewLifecycleOwner, Observer {
            it.let {
                resource ->  when(resource.status){
                Status.SUCCESS -> {
                    binding.imgDigimon.visibility = View.VISIBLE
                    binding.linearDataDetail.visibility = View.VISIBLE
                    binding.pbDetail.visibility= View.GONE

                    binding.tvNameDigimonDetail.text = it.data!![0]!!.name
                    binding.tvLevelDigimonDetail.text = it.data[0]!!.level
                    Glide.with(binding.imgDigimon.context)
                        .load(it.data[0]!!.img)
                        .into(binding.imgDigimon)
                }
                Status.LOADING -> {
                    binding.imgDigimon.visibility = View.GONE
                    binding.linearDataDetail.visibility = View.GONE
                    binding.pbDetail.visibility= View.VISIBLE
                }
                Status.ERROR -> {
                    binding.imgDigimon.visibility = View.GONE
                    binding.linearDataDetail.visibility = View.GONE
                    binding.pbDetail.visibility= View.GONE
                }
                }
            }
        })



    }

}