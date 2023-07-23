package com.example.svgassignment.utilities

import com.example.svgassignment.models.DogApiResponse

object DogCache {
    private const val cacheSize = 20
    private val cache = LinkedHashMap<String, DogApiResponse>(cacheSize, 0.75f, true)

    fun add(dogApiResponse: DogApiResponse) {
        cache[dogApiResponse.message] = dogApiResponse
        if (cache.size > cacheSize) {
            cache.remove(cache.entries.iterator().next().key)
        }
    }

    fun clear() {
        cache.clear()
    }

    fun getResponses(): List<DogApiResponse> {
        return cache.values.toList()
    }
}