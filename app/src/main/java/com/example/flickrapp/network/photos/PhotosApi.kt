package com.example.flickrapp.network.photos

import com.example.flickrapp.network.PhotosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApi {

    @GET("services/feeds/photos_public.gne")
    suspend fun getPhotos(
        @Query("format") format: String = "json",
        @Query("tags") tag: String = "cat",
        @Query("nojsoncallback") noJsonCallback: Int = 1
    ): PhotosResponse
}