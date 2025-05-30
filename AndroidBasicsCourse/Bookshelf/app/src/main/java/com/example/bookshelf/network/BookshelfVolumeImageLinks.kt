package com.example.bookshelf.network

import kotlinx.serialization.Serializable

@Serializable
data class BookshelfVolumeImageLinks(
    val imageLinks: BookshelfVolumeImageThumbnail
)