package com.example.psproject.presentation.digimonsdisplay.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.psproject.databinding.FragmentDigimonDetailBinding
import com.example.psproject.presentation.digimonsdisplay.viewmodel.DigimonDetailViewModel
import com.example.psproject.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DigimonDetailFragment : Fragment() {

    private lateinit var binding: FragmentDigimonDetailBinding
    private val args: DigimonDetailFragmentArgs by navArgs()
    private val digimonViewModel by viewModels<DigimonDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return FragmentDigimonDetailBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelSetUp()
        digimonViewModel.getDigimon(args.digimonName)
    }

    private fun viewModelSetUp() {
        digimonViewModel.digimon.observe(viewLifecycleOwner) {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.apply {
                            imgDigimon.visibility = View.VISIBLE
                            linearDataDetail.visibility = View.VISIBLE
                            pbDetail.visibility = View.GONE

                            tvNameDigimonDetail.text = it.data!![0].name
                            tvLevelDigimonDetail.text = it.data[0].level
                            Glide.with(imgDigimon.context)
                                .load(it.data[0].img)
                                .into(imgDigimon)
                        }
                    }
                    Status.LOADING -> {
                        binding.apply {
                            imgDigimon.visibility = View.GONE
                            linearDataDetail.visibility = View.GONE
                            pbDetail.visibility = View.VISIBLE
                        }
                    }
                    Status.ERROR -> {
                        binding.apply {
                            imgDigimon.visibility = View.GONE
                            linearDataDetail.visibility = View.GONE
                            pbDetail.visibility = View.GONE
                        }

                    }
                }
            }
        }
    }

}