package com.example.psproject.presentation.digimonsdisplay.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.psproject.databinding.FragmentDigimonListBinding
import com.example.psproject.presentation.digimonsdisplay.adapter.DigimonAdapter
import com.example.psproject.presentation.digimonsdisplay.uimodel.DigimonUIModel
import com.example.psproject.presentation.digimonsdisplay.viewmodel.DigimonsListViewModel
import com.example.psproject.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DigimonListFragment : Fragment() {

    private lateinit var digimonAdapter: DigimonAdapter
    private val digimonViewModel by viewModels<DigimonsListViewModel>()

    private lateinit var binding: FragmentDigimonListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return FragmentDigimonListBinding.inflate(inflater,container,false).also {
            binding =it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUI()
        setUpObservers()
        digimonViewModel.getAllDigimons()
    }

    private fun setUpUI(){
        digimonAdapter = DigimonAdapter(arrayListOf())
        binding.rvDigimonList.apply {
            layoutManager = LinearLayoutManager(binding.root.context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    (layoutManager as LinearLayoutManager).orientation
                )
            )
            adapter = digimonAdapter
        }
    }

    private fun setUpObservers() {

        digimonViewModel.digimons.observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.rvDigimonList.visibility = View.VISIBLE
                        binding.pbDigimonList.visibility = View.GONE
                        resource.data?.let { digimons -> retrieveList(digimons) }
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
        }
    }

    private fun gotToDetail(digimonId: String) {
        val actionToGoDetails: NavDirections =
            DigimonListFragmentDirections.
            actionDigimonListFragmentToDigimonDetailFragment(digimonId)

        findNavController().navigate(actionToGoDetails)
    }

    private fun retrieveList(digimons: List<DigimonUIModel>) {
        digimonAdapter.setItemClickListener {
            gotToDetail(it.name)
        }
        digimonAdapter.apply {
            addDigimonsToList(digimons)
            notifyDataSetChanged()
        }
    }
}