package com.example.flickrapp.photos.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.flickrapp.R
import com.example.flickrapp.databinding.ItemPhotoBinding
import com.example.flickrapp.persistance.database.photos.PhotoModel
import com.example.flickrapp.utils.CoilImageGetter

class PhotoViewHolder(private val binding: ItemPhotoBinding, private val onClick: ((String) -> Unit)) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: PhotoModel) = with(binding) {
        root.setOnClickListener { onClick(data.contentUrl) }
        description.text = HtmlCompat.fromHtml(data.description, HtmlCompat.FROM_HTML_MODE_COMPACT, CoilImageGetter(description), null)
        image.load(data.photoUrl) {
            placeholder(R.mipmap.ic_launcher)
            error(R.mipmap.ic_launcher)
        }
    }

    companion object {
        fun createHolder(parent: ViewGroup, onClick: ((String) -> Unit)): PhotoViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemPhotoBinding.inflate(inflater, parent, false)
            return PhotoViewHolder(binding, onClick)
        }
    }
}