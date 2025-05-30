package com.example.mycity.ui

import com.example.mycity.data.Category
import com.example.mycity.data.Recommendation
import com.example.mycity.data.local.LocalCategoriesDataProvider
import com.example.mycity.data.local.LocalRecommendationDataProvider

data class MyCityUiState(
    val categories: List<Category> = LocalCategoriesDataProvider.allCategories,
    val recommendations: Map<Category, List<Recommendation>> = emptyMap(),
    val allRecommendations: List<Recommendation> = LocalRecommendationDataProvider.allRecommendations,
    val currentSelectedCategory: Category = LocalCategoriesDataProvider.defaultCategory,
    val currentSelectedRecommendation: Recommendation = LocalRecommendationDataProvider.defaultRecommendation,
    val isShowingHomepage: Boolean = true
) {
    val currentRecommendations: List<Recommendation> by lazy {
        recommendations[currentSelectedCategory]!!
    }
}