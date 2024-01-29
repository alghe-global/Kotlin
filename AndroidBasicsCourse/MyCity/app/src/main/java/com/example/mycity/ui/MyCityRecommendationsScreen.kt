package com.example.mycity.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.mycity.R
import com.example.mycity.data.Recommendation

@Composable
fun MyCityRecommendationsScreen(
    recommendations: List<Recommendation>,
    myCityUiState: MyCityUiState,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = { navController.navigateUp() },
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface, shape = CircleShape)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(R.string.navigation_back)
        )
    }
    LazyColumn(
        modifier = modifier
            .padding(
                end = dimensionResource(R.dimen.list_and_detail_list_padding_end),
                top = dimensionResource(R.dimen.detail_body_padding_end)
            ),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.recommendation_list_padding)
        )
    ) {
        items(recommendations, key = { recommendation -> recommendation.id
        }) { recommendation ->
            MyCityRecommendationListItem(
                recommendation = recommendation,
                selected =
                myCityUiState.currentSelectedRecommendation.id == recommendation.id,
                onCardClick = {
                    navController.navigate(
                        MyCityScreen.Recommendation.createRoute(recommendation.id)
                    )
                }
            )
        }
    }
}