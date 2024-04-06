package com.example.flickrapp.di.modules

import com.example.flickrapp.utils.DefaultDispatchersProvider
import com.example.flickrapp.utils.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DispatchersModule {

    @Provides
    @Singleton
    fun provideDispatchers(): DispatchersProvider = DefaultDispatchersProvider()

}