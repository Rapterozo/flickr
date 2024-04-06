package com.example.flickrapp.network.photos

import com.example.flickrapp.network.NetworkPhoto
import java.io.IOException

interface PhotosService {
    @Throws(IOException::class)
    suspend fun getPhotos(): List<NetworkPhoto>
}