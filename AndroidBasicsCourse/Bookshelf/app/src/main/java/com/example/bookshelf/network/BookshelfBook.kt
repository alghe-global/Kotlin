package com.example.bookshelf.network

import kotlinx.serialization.Serializable

@Serializable
data class BookshelfBook(
    val id: String
)