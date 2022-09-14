package com.example.psproject.presentation.digimonsdisplay.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.psproject.R
import com.example.psproject.presentation.digimonsdisplay.uimodel.DigimonUIModel

class DigimonAdapter(private val digimons: ArrayList<DigimonUIModel>) :
    RecyclerView.Adapter<DigimonAdapter.ViewHolder>() {

    private lateinit var onClickListener: ((DigimonUIModel) -> Unit)

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView
        private val lvlTextView: TextView
        private val imageView: ImageView


        init {
            textView = view.findViewById(R.id.digimonTextViewItem)
            imageView = view.findViewById(R.id.imageView)
            lvlTextView = view.findViewById(R.id.lvltextView)
        }

        fun bind(digimon: DigimonUIModel){
            itemView.apply{
                textView.text = digimon.name
                lvlTextView.text = "Level: "+digimon.level
                Glide.with(imageView.context)
                    .load(digimon.img)
                    .into(imageView)
            }
            itemView.setOnClickListener { onClickListener(digimon) }
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int, ) {
            viewHolder.bind(digimons[position])
    }
    override fun getItemCount() = digimons.size

    fun setItemClickListener(listener: ((DigimonUIModel) -> Unit)) {
        onClickListener = listener
    }

    fun addDigimonsToList(newDigimons:List<DigimonUIModel>?){
        this.digimons.apply {
            clear()
            if (newDigimons != null) {
                addAll(newDigimons)
            }
        }
    }
}