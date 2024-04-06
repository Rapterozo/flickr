package com.example.flickrapp.network.photos

import com.example.flickrapp.network.NetworkPhoto

class PhotosServiceImpl(private val api: PhotosApi) : PhotosService {

    override suspend fun getPhotos(): List<NetworkPhoto> {
        return api.getPhotos().items ?: emptyList()
    }
}