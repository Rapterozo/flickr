package com.example.flickrapp.photos.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.flickrapp.persistance.database.photos.PhotoModel

internal class PhotoAdapter(private val onClick: ((String) -> Unit)) : ListAdapter<PhotoModel, PhotoViewHolder>(PhotoModelDiffCallback()) {

    var data: List<PhotoModel> = emptyList()
        set(value) {
            field = value
            submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder.createHolder(parent, onClick)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(data[position])
    }

    private class PhotoModelDiffCallback : DiffUtil.ItemCallback<PhotoModel>() {
        override fun areItemsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean {
            return oldItem.contentUrl == newItem.contentUrl
        }

        override fun areContentsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean {
            return oldItem == newItem
        }
    }
}