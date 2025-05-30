package com.example.mycity.data.local

import com.example.mycity.R
import com.example.mycity.data.Category

/**
 * A static data store of [Category]s
 */

object LocalCategoriesDataProvider {

    val allCategories = listOf(
        Category(
            id = 0L,
            name = R.string.category_1_name,
            image = R.drawable.category_1
        ),
        Category(
            id = 1L,
            name = R.string.category_2_name,
            image = R.drawable.category_2
        ),
        Category(
            id = 2L,
            name = R.string.category_3_name,
            image = R.drawable.category_3
        ),
        Category(
            id = 3L,
            name = R.string.category_4_name,
            image = R.drawable.category_4
        )
    )

    val defaultCategory = getCategoryById(0L)

    /**
     * Returns [Category] based on id
     */
    fun getCategoryById(categoryId: Long): Category {
        return allCategories.firstOrNull { it.id == categoryId }
            ?: allCategories.first()
    }
}