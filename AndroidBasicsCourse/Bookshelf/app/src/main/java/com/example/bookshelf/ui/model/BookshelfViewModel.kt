package com.example.bookshelf.ui.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookshelfApplication
import com.example.bookshelf.data.BookshelfRepository
import com.example.bookshelf.network.BookshelfVolumeImageThumbnail
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface BookshelfUiState {
    data class Success(val thumbnails: List<BookshelfVolumeImageThumbnail>): BookshelfUiState
    object Loading: BookshelfUiState
    object Error: BookshelfUiState
}

class BookshelfViewModel(
    private val bookshelfRepository: BookshelfRepository
) : ViewModel() {
    var bookshelfUiState: BookshelfUiState by mutableStateOf(BookshelfUiState.Loading)
        private set

    init {
        getBookshelfVolumes()
    }

    fun getBookshelfVolumes() {
        viewModelScope.launch {
            bookshelfUiState = try {
                val booksResponse = bookshelfRepository.getBookshelfBooks()
                val booksIds = booksResponse.items.map { it.id }
                val volumes = booksIds.map { id ->
                    bookshelfRepository.getBookshelfVolume(id)
                }
                val volumesInfo = volumes.map { it.volumeInfo }
                val imageLinks = volumesInfo.map { it.imageLinks }
                BookshelfUiState.Success(imageLinks)
            } catch (e: IOException) {
                BookshelfUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookshelfApplication)
                val bookshelfRepository = application.container.bookshelfRepository
                BookshelfViewModel(bookshelfRepository = bookshelfRepository)
            }
        }
    }
}