package com.example.svgassignment.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.svgassignment.models.DogApiResponse
import com.example.svgassignment.repository.DogRepository
import com.example.svgassignment.utilities.DogCache
import com.example.svgassignment.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecentlyGeneratedDogsViewModel @Inject constructor(private val dogRepository: DogRepository) : ViewModel() {
    private val _dogImagesLiveData = MutableLiveData<Resource<List<DogApiResponse>>>()
    val dogImagesLiveData: LiveData<Resource<List<DogApiResponse>>>
        get() = _dogImagesLiveData

    fun loadDogImages() {
        _dogImagesLiveData.value = Resource.Loading(null)
        _dogImagesLiveData.value = Resource.Success(DogCache.getResponses())
    }

    fun clearDogCache() {
        DogCache.clear()
        _dogImagesLiveData.value = Resource.Success(emptyList())
    }
}