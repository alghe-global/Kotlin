package com.example.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Course(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    val numberOfItems: Int
)