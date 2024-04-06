package com.example.flickrapp.photos

import android.util.Log
import com.example.flickrapp.network.NetworkPhoto
import com.example.flickrapp.network.photos.PhotosService
import com.example.flickrapp.persistance.database.AppDatabase
import com.example.flickrapp.persistance.database.photos.PhotoModel
import com.example.flickrapp.utils.DispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotosRepository @Inject constructor(
    private val photosService: PhotosService,
    private val appDatabase: AppDatabase,
    private val dispatchersProvider: DispatchersProvider
) {

    private companion object {
        const val TAG = "PhotosRepository"
    }

    suspend fun loadPhotos() {
        withContext(dispatchersProvider.io) {
            runCatching {
                photosService.getPhotos()
            }.onSuccess {
                appDatabase.photoDao().addPhotos(it.mapNotNull { it.toDomain() })
            }.onFailure {
                Log.e(TAG, "Error loading photos from backend", it)
            }
        }
    }

    /*TODO Pagination?*/
    fun getPhotos(): Flow<List<PhotoModel>> = appDatabase.photoDao().getAllPhotos()

    private fun NetworkPhoto.toDomain(): PhotoModel? {
        return if (link != null && description != null && published != null && media?.photoUrl != null) {
            PhotoModel(photoUrl = media.photoUrl, contentUrl = link, description = description, published = published.toEpochMilliseconds())
        } else null
    }
}