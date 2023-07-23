package com.example.svgassignment.api

import com.example.svgassignment.models.DogApiResponse
import retrofit2.http.GET

interface DogApiService {

    @GET("breeds/image/random")
    suspend fun getDogImage(): DogApiResponse

}