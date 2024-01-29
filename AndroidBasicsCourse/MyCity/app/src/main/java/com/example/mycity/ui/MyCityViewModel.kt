package com.example.mycity.ui

import androidx.lifecycle.ViewModel
import com.example.mycity.data.Category
import com.example.mycity.data.Recommendation
import com.example.mycity.data.local.LocalCategoriesDataProvider
import com.example.mycity.data.local.LocalRecommendationDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MyCityViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MyCityUiState())
    val uiState: StateFlow<MyCityUiState> = _uiState

    init {
        initializeUiState()
    }

    private fun initializeUiState() {
        val categories: List<Category> = LocalCategoriesDataProvider.allCategories
        val recommendations: Map<Category, List<Recommendation>> = mapOf(
            categories[0] to LocalRecommendationDataProvider.allRecommendations.filter {
                it.category == categories[0]
            },
            categories[1] to LocalRecommendationDataProvider.allRecommendations.filter {
                it.category == categories[1]
            },
            categories[2] to LocalRecommendationDataProvider.allRecommendations.filter {
                it.category == categories[2]
            },
            categories[3] to LocalRecommendationDataProvider.allRecommendations.filter {
                it.category == categories[3]
            }
        )
        _uiState.value = MyCityUiState(
            categories = categories,
            recommendations = recommendations
        )
    }

    fun updateDetailsScreenStates(category: Category, recommendation: Recommendation) {
        _uiState.update {
            it.copy(
                currentSelectedCategory = category,
                currentSelectedRecommendation = recommendation,
                isShowingHomepage = false
            )
        }
    }

    fun updateRecommendations(category: Category) {
        _uiState.update {
            it.copy(
                currentSelectedCategory = category,
                isShowingHomepage = false
            )
        }
    }
}