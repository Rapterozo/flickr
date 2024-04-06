package com.example.flickrapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickrapp.persistance.database.photos.PhotoModel
import com.example.flickrapp.photos.PhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface MainState {
    data object Loading : MainState
    data class Loaded(val content: List<PhotoModel>) : MainState
}

@HiltViewModel
class MainViewModel @Inject constructor(private val photosRepository: PhotosRepository) : ViewModel() {

    private var _state: MutableStateFlow<MainState> = MutableStateFlow(MainState.Loading)
    val state: Flow<MainState> = _state.asStateFlow()

    init {
        observePhotos()
        viewModelScope.launch {
            photosRepository.loadPhotos()
        }
    }

    private fun observePhotos() {
        photosRepository.getPhotos()
            .onStart { _state.value = MainState.Loading }
            .onEach { _state.value = MainState.Loaded(it) }
            .launchIn(viewModelScope)
    }
}