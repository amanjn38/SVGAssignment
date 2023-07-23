package com.example.svgassignment.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.svgassignment.models.DogApiResponse
import com.example.svgassignment.repository.DogRepository
import com.example.svgassignment.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateDogsViewModel @Inject constructor(private val dogRepository: DogRepository) : ViewModel() {
    private val _dogImageLiveData = MutableLiveData<Resource<DogApiResponse>>()
    val dogImageLiveData: LiveData<Resource<DogApiResponse>>
        get() = _dogImageLiveData

    fun generateDogImage() {
        viewModelScope.launch {
            _dogImageLiveData.value = Resource.Loading(null)
            try {
                val response = dogRepository.getDogImage()
                _dogImageLiveData.postValue(response)
            } catch (e: Exception) {
                _dogImageLiveData.postValue(Resource.Error("Failed to generate dog image", null))
            }
        }
    }
}
