package com.example.mycity.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
fun MyCityRecommendationDetailScreen(
    recommendation: Recommendation,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = { navController.navigateUp() },
        modifier = modifier
            .padding(
                horizontal = dimensionResource(
                    R.dimen.detail_topbar_back_button_padding_horizontal
                )
            )
            .background(MaterialTheme.colorScheme.surface, shape = CircleShape)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(R.string.navigation_back)
        )
    }
    MyCityRecommendationDetailsCard(
        recommendation = recommendation,
        isFullScreen = false,
        modifier = Modifier
            .padding(top = dimensionResource(R.dimen.detail_body_padding_end))
            .verticalScroll(rememberScrollState())
    )
}