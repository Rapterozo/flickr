package com.example.flickrapp.persistance.database.photos

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class PhotoModel(
    @PrimaryKey val contentUrl: String,
    val photoUrl: String,
    val description: String,
    val published: Long,
)