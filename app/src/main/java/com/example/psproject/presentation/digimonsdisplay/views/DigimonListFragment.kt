package com.example.psproject.presentation.digimonsdisplay.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.psproject.R
import com.example.psproject.data.model.DigimonModel
import com.example.psproject.databinding.FragmentDigimonListBinding
import com.example.psproject.presentation.digimonsdisplay.adapter.DigimonAdapter
import com.example.psproject.presentation.digimonsdisplay.viewmodel.DigimonsViewmodel
import com.example.psproject.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DigimonListFragment : Fragment() {

    @Inject
    lateinit var digimonAdapter: DigimonAdapter
    private val digimonViewModel by viewModels<DigimonsViewmodel>()

    private lateinit var binding: FragmentDigimonListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDigimonListBinding.inflate(inflater,container,false)

        setUpUI()
        digimonViewModel.getAllDigimons()
        setUpObservers()
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        return binding.root
    }

    private fun setUpUI(){
        binding.rvDigimonList.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvDigimonList.addItemDecoration(
            DividerItemDecoration(
                binding.rvDigimonList.context,
                ( binding.rvDigimonList.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.rvDigimonList.adapter = digimonAdapter
    }

    private fun setUpObservers() = digimonViewModel.digimons.observe(viewLifecycleOwner, Observer{
        it?.let {
                resource ->
            when(resource.status){
                Status.SUCCESS -> {
                    binding.rvDigimonList.visibility = View.VISIBLE
                    binding.pbDigimonList.visibility = View.GONE
                    resource.data?.let{
                            digimons->retrieveList(digimons)}
                }
                Status.ERROR -> {
                    binding.rvDigimonList.visibility = View.GONE
                    binding.pbDigimonList.visibility = View.GONE

                    Toast.makeText(binding.root.context, it.message, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    binding.rvDigimonList.visibility = View.GONE
                    binding.rvDigimonList.visibility = View.VISIBLE
                }
            }
        }
    })

    private fun gotToDetail(digimonId: String) {
        val actionToGoDetails: NavDirections =
            DigimonListFragmentDirections.
            actionDigimonListFragmentToDigimonDetailFragment(digimonId)

        findNavController().navigate(actionToGoDetails)
    }

    private fun retrieveList(digimons: List<DigimonModel>) {
        digimonAdapter.setItemClickListener {
            gotToDetail(it.name)
        }
        digimonAdapter.apply {
            addDigimonsToList(digimons)
            notifyDataSetChanged()
        }
    }
}