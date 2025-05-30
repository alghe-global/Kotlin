package com.example.bookshelf.network

import retrofit2.http.GET
import retrofit2.http.Path

interface BookshelfApiService {
    @GET("?q=jazz+history")
    suspend fun getBooks(): BookshelfResponse

    @GET("{volume_id}")
    suspend fun getVolume(@Path("volume_id") id: String): BookshelfVolume
}