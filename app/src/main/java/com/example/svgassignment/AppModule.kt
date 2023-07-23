package com.example.svgassignment

import com.example.svgassignment.api.DogApiService
import com.example.svgassignment.repository.DogRepository
import com.example.svgassignment.repository.DogRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDogApiService(): DogApiService {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDogRepository(
        dogApiService: DogApiService
    ): DogRepository {
        return DogRepositoryImpl(dogApiService)
    }
}