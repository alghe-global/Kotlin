package com.example.bookshelf.data

import com.example.bookshelf.network.BookshelfApiService
import com.example.bookshelf.network.BookshelfBook
import com.example.bookshelf.network.BookshelfVolume

interface BookshelfRepository {
    suspend fun getBookshelfBooks(): List<BookshelfBook>
    suspend fun getBookshelfVolume(id: String): BookshelfVolume
}

class NetworkBookshelfRepository(
    private val bookshelfApiService: BookshelfApiService
) : BookshelfRepository {
    override suspend fun getBookshelfBooks(): List<BookshelfBook> =
        bookshelfApiService.getBooks()

    override suspend fun getBookshelfVolume(id: String): BookshelfVolume =
        bookshelfApiService.getVolume(id)
}