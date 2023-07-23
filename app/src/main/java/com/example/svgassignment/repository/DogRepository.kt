package com.example.svgassignment.repository

import com.example.svgassignment.models.DogApiResponse
import com.example.svgassignment.utilities.Resource
import retrofit2.http.GET

interface DogRepository {
    @GET("breeds/image/random")
    suspend fun getDogImage(): Resource<DogApiResponse>
}