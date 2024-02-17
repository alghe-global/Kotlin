package com.example.bookshelf.network

import retrofit2.http.GET
import retrofit2.http.Path

interface BookshelfApiService {
    @GET("?q=jazz+history")
    suspend fun getBooks(): List<BookshelfBook>

    @GET("{volume_id}")
    suspend fun getVolume(@Path("id") id: String): BookshelfVolume
}