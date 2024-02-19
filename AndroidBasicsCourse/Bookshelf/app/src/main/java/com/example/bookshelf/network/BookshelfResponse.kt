package com.example.bookshelf.network

import kotlinx.serialization.Serializable

@Serializable
data class BookshelfResponse(
    val items: List<BookshelfBook>
)