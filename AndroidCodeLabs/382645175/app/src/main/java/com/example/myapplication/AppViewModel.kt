package com.example.myapplication

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {

    private val _isRefreshing = mutableStateOf(false)
    val isRefreshing = _isRefreshing

    fun setIsRefreshing(isRefreshing: Boolean) {
        _isRefreshing.value = isRefreshing
    }
}