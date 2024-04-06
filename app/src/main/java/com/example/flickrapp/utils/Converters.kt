package com.example.flickrapp.utils

import androidx.room.TypeConverter
import kotlinx.datetime.Instant

class Converters {
    @TypeConverter
    fun stringToLong(date: String?): Long? {
        return date?.let { Instant.parse(it) }?.toEpochMilliseconds()
    }

    @TypeConverter
    fun longToString(date: Long?): String? {
        return date?.let { Instant.fromEpochMilliseconds(it).toString() }
    }
}