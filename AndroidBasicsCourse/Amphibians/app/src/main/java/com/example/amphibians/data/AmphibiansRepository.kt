package com.example.amphibians.data

import com.example.amphibians.network.AmphibiansApiService
import com.example.amphibians.network.AmphibiansPhoto

interface AmphibiansRepository {
    suspend fun getAmphibiansPhotos(): List<AmphibiansPhoto>
}

class NetworkAmphibiansRepository (
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {
    override suspend fun getAmphibiansPhotos(): List<AmphibiansPhoto> =
        amphibiansApiService.getPhotos()
}