package com.example.svgassignment.repository

import com.example.svgassignment.api.DogApiService
import com.example.svgassignment.models.DogApiResponse
import com.example.svgassignment.utilities.DogCache
import com.example.svgassignment.utilities.Resource
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(
    private val dogApiService: DogApiService
) : DogRepository {
    override suspend fun getDogImage(): Resource<DogApiResponse> {
        return try {
            val response = dogApiService.getDogImage()
            if (response.status == "success") {
                DogCache.add(response)
                Resource.Success(response)
            } else {
                Resource.Error("Failed to fetch dog image", null)
            }
        } catch (e: Exception) {
            Resource.Error("Failed to fetch dog image", null)
        }
    }

}