package com.example.psproject.presentation.digimonsdisplay.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.psproject.databinding.TextRowItemBinding
import com.example.psproject.presentation.digimonsdisplay.uimodel.DigimonUIModel

class DigimonViewHolder(
    private val binding: TextRowItemBinding,
    private val listener:((DigimonUIModel) -> Unit)
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(digimon: DigimonUIModel){
        binding.apply{
            digimonTextViewItem.text = digimon.name
            lvltextView.text = "Level: "+digimon.level
            Glide.with(imageView.context)
                .load(digimon.img)
                .into(imageView)
            root.setOnClickListener { listener(digimon) }
        }
    }
}