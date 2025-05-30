package com.example.mycity.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavController
import com.example.mycity.R
import com.example.mycity.data.Category

@Composable
fun MyCityCategoriesScreen(
    categories: List<Category>,
    myCityUiState: MyCityUiState,
    viewModel: MyCityViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(
                end = dimensionResource(R.dimen.list_and_detail_list_padding_end),
                top = dimensionResource(R.dimen.list_and_detail_list_padding_top)
            ),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.recommendation_list_padding)
        )
    ) {
        items(categories, key = { category -> category.id }) { category ->
            MyCityCategoryListItem(
                category = category,
                selected = myCityUiState.currentSelectedRecommendation.id == category.id,
                onCardClick = {
                    viewModel.updateRecommendations(category)
                    navController.navigate(MyCityScreen.Recommendations.route)
                }
            )
        }
    }
}