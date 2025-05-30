package com.example.mycity.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.mycity.data.local.LocalCategoriesDataProvider

data class Recommendation(
    /** Unique ID of the recommendation */
    val id: Long,
    /** Location of the recommendation */
    @StringRes val location: Int = -1,
    /** What makes this recommendation valuable */
    @StringRes val offer: Int = -1,
    /** Content of the recommendation */
    @StringRes val body: Int = -1,
    /** Accessibility information part of the recommendation */
    @StringRes val accessibility: Int = -1,
    /** Recommendation type */
    var type: RecommendationType = RecommendationType.CITY,
    /** Recommendation category */
    var category: Category = LocalCategoriesDataProvider.defaultCategory,
    /** Recommendation's image resource id */
    @DrawableRes val image: Int = -1,
    /** When was this recommendation created */
    var createdAt: Int = -1
)