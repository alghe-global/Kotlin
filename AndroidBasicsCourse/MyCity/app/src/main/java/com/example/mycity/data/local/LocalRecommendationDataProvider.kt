package com.example.mycity.data.local

import com.example.mycity.R
import com.example.mycity.data.Recommendation
import com.example.mycity.data.RecommendationType

/**
 * A static data store of [Recommendation]s
 */

object LocalRecommendationDataProvider {

    val allRecommendations = listOf(
        Recommendation(
            id = 0L,
            location = R.string.recommendation_1_location,
            offer = R.string.recommendation_1_offer,
            body = R.string.recommendation_1_body,
            accessibility = R.string.recommendation_1_accessibility,
            type = RecommendationType.CITY,
            category = LocalCategoriesDataProvider.getCategoryById(0L),
            image = R.drawable.recommendation_1,
            createdAt = R.string.recommendation_1_time
        ),
        Recommendation(
            id = 1L,
            location = R.string.recommendation_2_location,
            offer = R.string.recommendation_2_offer,
            body = R.string.recommendation_2_body,
            accessibility = R.string.recommendation_2_accessibility,
            type = RecommendationType.COUNTRY,
            category = LocalCategoriesDataProvider.getCategoryById(1L),
            image = R.drawable.recommendation_2,
            createdAt = R.string.recommendation_2_time
        ),
        Recommendation(
            id = 2L,
            location = R.string.recommendation_3_location,
            offer = R.string.recommendation_3_offer,
            body = R.string.recommendation_3_body,
            accessibility = R.string.recommendation_3_accessibility,
            type = RecommendationType.CITY,
            category = LocalCategoriesDataProvider.getCategoryById(2L),
            image = R.drawable.recommendation_3,
            createdAt = R.string.recommendation_3_time
        ),
        Recommendation(
            id = 3L,
            location = R.string.recommendation_4_location,
            offer = R.string.recommendation_4_offer,
            body = R.string.recommendation_4_body,
            type = RecommendationType.INTERNATIONAL,
            category = LocalCategoriesDataProvider.getCategoryById(3L),
            image = R.drawable.recommendation_4,
            createdAt = R.string.recommendation_4_time
        ),
        Recommendation(
            id = 4L,
            location = R.string.recommendation_5_location,
            offer = R.string.recommendation_5_offer,
            body = R.string.recommendation_5_body,
            accessibility = R.string.recommendation_5_accessibility,
            type = RecommendationType.INTERNATIONAL,
            category = LocalCategoriesDataProvider.getCategoryById(3L),
            image = R.drawable.recommendation_5,
            createdAt = R.string.recommendation_5_time
        )
    )

    val defaultRecommendation = getRecommendationById(0L)

    /**
     * Returns [Recommendation] based on id
     */
    private fun getRecommendationById(recommendationId: Long): Recommendation {
        return allRecommendations.firstOrNull { it.id == recommendationId }
            ?: allRecommendations.first()
    }
}