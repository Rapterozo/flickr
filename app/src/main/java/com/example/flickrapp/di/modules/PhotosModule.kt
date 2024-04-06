package com.example.flickrapp.di.modules

import com.example.flickrapp.network.photos.PhotosApi
import com.example.flickrapp.network.photos.PhotosService
import com.example.flickrapp.network.photos.PhotosServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PhotosModule {

    @Provides
    @Singleton
    fun providePhotosApi(retrofit: Retrofit): PhotosApi = retrofit.create(PhotosApi::class.java)

    @Provides
    @Singleton
    fun providePhotosService(api: PhotosApi): PhotosService = PhotosServiceImpl(api)
}