package com.example.psproject.presentation.digimonsdisplay.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.psproject.R
import com.example.psproject.databinding.TextRowItemBinding
import com.example.psproject.presentation.digimonsdisplay.uimodel.DigimonUIModel

class DigimonAdapter(
    private val digimons: ArrayList<DigimonUIModel>
    ) : RecyclerView.Adapter<DigimonViewHolder>() {

    private lateinit var onClickListener: ((DigimonUIModel) -> Unit)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): DigimonViewHolder {
        val binding =
            TextRowItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return DigimonViewHolder(binding = binding, listener = onClickListener)
    }

    override fun onBindViewHolder(viewHolder: DigimonViewHolder, position: Int) {
        viewHolder.bind(digimons[position])
    }

    override fun getItemCount() = digimons.size

    fun setItemClickListener(listener: ((DigimonUIModel) -> Unit)) {
        onClickListener = listener
    }

    fun addDigimonsToList(newDigimons: List<DigimonUIModel>?) =
        this.digimons.apply {
            clear()
            if (newDigimons != null) {
                addAll(newDigimons)
            }
        }
}