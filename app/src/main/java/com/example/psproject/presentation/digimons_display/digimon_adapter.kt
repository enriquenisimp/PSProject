package com.example.psproject.presentation.digimons_display

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.psproject.R
import com.example.psproject.data.model.DigimonModel

class DigimonAdapter(private val digimons: ArrayList<DigimonModel>) :
    RecyclerView.Adapter<DigimonAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView
        private val lvltextView: TextView
        private val imageView: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.digimonTextViewItem)
            imageView = view.findViewById(R.id.imageView)
            lvltextView = view.findViewById(R.id.lvltextView)
        }

        fun bind(digimonModel: DigimonModel){
            itemView.apply{
                textView.text = digimonModel.name
                lvltextView.text = "Level: "+digimonModel.level
                Glide.with(imageView.context)
                    .load(digimonModel.img)
                    .into(imageView)
            }
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int, ) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
            viewHolder.bind(digimons[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = digimons.size

    fun addDigimonsToList(newDigimons:List<DigimonModel>?){
        this.digimons.apply {
            clear()
            if (newDigimons != null) {
                addAll(newDigimons)
            }
        }
    }
}