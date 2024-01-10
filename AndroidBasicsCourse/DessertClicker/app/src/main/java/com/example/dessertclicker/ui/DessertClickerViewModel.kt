package com.example.dessertclicker.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.dessertList
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private val desserts: List<Dessert> = dessertList

class DessertClickerViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DessertClickerUiState())
    val uiState: StateFlow<DessertClickerUiState> = _uiState.asStateFlow()

    var revenue by mutableStateOf(0)
        private set

    var dessertsSold by mutableStateOf(0)
        private set

    var currentDessertIndex by mutableStateOf(0)
        private set

    var currentDessertImageId by mutableStateOf(
        desserts[currentDessertIndex].imageId
    )
        private set

    var currentDessertPrice by mutableStateOf(
        desserts[currentDessertIndex].price
    )
        private set

    fun updateRevenue() {
        // Update the revenue
        revenue += currentDessertPrice
        _uiState.update { currentState ->
            currentState.copy(
                revenue = revenue
            )
        }
    }

    fun updateDessertsSold() {
        dessertsSold++
        _uiState.update { currentState ->
            currentState.copy(
                dessertsSold = dessertsSold
            )
        }
    }

    fun showNextDessert() {
        // Show the next dessert
        val dessertToShow = determineDessertToShow(desserts, dessertsSold)

        if (dessertToShow != desserts[currentDessertIndex]) {
            currentDessertIndex = desserts.indexOf(dessertToShow)
        }

        currentDessertImageId = dessertToShow.imageId
        currentDessertPrice = dessertToShow.price

        _uiState.update { currentState ->
            currentState.copy(
                currentDessertIndex = currentDessertIndex,
                currentDessertImageId = currentDessertImageId,
                currentDessertPrice = currentDessertPrice
            )
        }
    }

    fun resetScreen() {
        _uiState.value = DessertClickerUiState()
    }

    init {
        resetScreen()
    }
}

/**
 * Determine which dessert to show.
 */
fun determineDessertToShow(
    desserts: List<Dessert>,
    dessertsSold: Int
): Dessert {
    var dessertToShow = desserts.first()
    for (dessert in desserts) {
        if (dessertsSold >= dessert.startProductionAmount) {
            dessertToShow = dessert
        } else {
            // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
            // you'll start producing more expensive desserts as determined by startProductionAmount
            // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
            // than the amount sold.
            break
        }
    }

    return dessertToShow
}