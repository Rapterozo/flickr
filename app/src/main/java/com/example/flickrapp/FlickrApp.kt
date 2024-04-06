package com.example.flickrapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FlickrApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}