package com.example.bookshelf.network

import kotlinx.serialization.Serializable

@Serializable
data class BookshelfVolumeImageThumbnail(
    val thumbnail: String
)