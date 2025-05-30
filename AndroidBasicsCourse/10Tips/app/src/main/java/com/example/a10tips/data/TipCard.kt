package com.example.a10tips.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.a10tips.R

/**
 * A data class to represent the information in a Card
 */
data class TipCard(
    @DrawableRes val imageResourceId: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
)

val cards = listOf(
    TipCard(R.drawable.one, R.string.one, R.string.one_description),
    TipCard(R.drawable.two, R.string.two, R.string.two_description),
    TipCard(R.drawable.three, R.string.three, R.string.three_description),
    TipCard(R.drawable.four, R.string.four, R.string.four_description),
    TipCard(R.drawable.five, R.string.five, R.string.five_description),
    TipCard(R.drawable.six, R.string.six, R.string.six_description),
    TipCard(R.drawable.seven, R.string.seven, R.string.seven_description),
    TipCard(R.drawable.eight, R.string.eight, R.string.eight_description),
    TipCard(R.drawable.nine, R.string.nine, R.string.nine_description),
    TipCard(R.drawable.ten, R.string.ten, R.string.ten_description)
)