package com.example.bookshelf.network

import kotlinx.serialization.Serializable

@Serializable
data class BookshelfVolume(
    val imageLinks: Map<String, String>
)