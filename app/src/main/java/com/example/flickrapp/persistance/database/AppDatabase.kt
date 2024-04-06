package com.example.flickrapp.persistance.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.flickrapp.persistance.database.photos.PhotoDao
import com.example.flickrapp.persistance.database.photos.PhotoModel
import com.example.flickrapp.utils.Converters

@Database(entities = [PhotoModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}