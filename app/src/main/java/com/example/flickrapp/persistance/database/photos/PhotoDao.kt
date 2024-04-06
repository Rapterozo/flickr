package com.example.flickrapp.persistance.database.photos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photomodel ORDER BY published DESC")
    fun getAllPhotos(): Flow<List<PhotoModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPhotos(photos: List<PhotoModel>)
}