package com.example.svgassignment.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.svgassignment.R
import com.example.svgassignment.models.DogApiResponse
import com.example.svgassignment.utilities.DataDiffCallback

class DogImageAdapter : RecyclerView.Adapter<DogImageAdapter.DogImageViewHolder>() {

    private val dogImages: MutableList<DogApiResponse> = mutableListOf()

    fun submitList(newDogImages: List<DogApiResponse>) {
        val diffResult = DiffUtil.calculateDiff(DataDiffCallback(dogImages, newDogImages))
        dogImages.clear()
        dogImages.addAll(newDogImages)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_dog_image, parent, false)
        return DogImageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DogImageViewHolder, position: Int) {
        val dogApiResponse = dogImages[position]
        holder.bind(dogApiResponse)
    }

    override fun getItemCount(): Int {
        return dogImages.size
    }

    class DogImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.dogImage)

        fun bind(dogApiResponse: DogApiResponse) {
            // Load and display the dog image into the ImageView
            Glide.with(itemView.context)
                .load(dogApiResponse.message)
                .into(imageView)
        }
    }
}
