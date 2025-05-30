package com.example.mycity.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Category(
    /** Unique ID of a category */
    val id: Long,
    /** Name of the category */
    @StringRes val name: Int,
    /** Category's image resource id */
    @DrawableRes val image: Int
)