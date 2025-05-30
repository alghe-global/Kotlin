package com.example.mycity.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.mycity.R
import com.example.mycity.data.Recommendation

@Composable
fun MyCityDetailsScreen(
    myCityUiState: MyCityUiState,
    isFullScreen: Boolean,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackPressed()
    }
    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                .padding(top = dimensionResource(R.dimen.detail_card_list_padding_top))
        ) {
            item {
                if (isFullScreen) {
                    MyCityDetailsScreenTopBar(
                        onBackPressed,
                        myCityUiState,
                        Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = dimensionResource(
                                    R.dimen.detail_topbar_padding_bottom
                                )
                            )
                    )
                }
                MyCityRecommendationDetailsCard(
                    recommendation = myCityUiState.currentSelectedRecommendation,
                    isFullScreen = isFullScreen,
                    modifier = if (isFullScreen) {
                        Modifier.padding(
                            horizontal = dimensionResource(
                                R.dimen.detail_card_outer_padding_horizontal
                            )
                        )
                    } else {
                        Modifier.padding(
                            end = dimensionResource(
                                R.dimen.detail_card_outer_padding_horizontal
                            )
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun MyCityDetailsScreenTopBar(
    onBackButtonClicked: () -> Unit,
    myCityUiState: MyCityUiState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top
    ) {
        IconButton(
            onClick = onBackButtonClicked,
            modifier = Modifier
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
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = dimensionResource(R.dimen.detail_body_padding_end))
        ) {
            Text(
                text = stringResource(myCityUiState.currentSelectedRecommendation.body),
                maxLines = 2,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun MyCityRecommendationDetailsCard(
    recommendation: Recommendation,
    isFullScreen: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.detail_card_inner_padding))
    ) {
        MyCityDetailsScreenHeader(
            recommendation,
            Modifier.fillMaxWidth()
        )
        if (isFullScreen) {
            Spacer(modifier =
                Modifier.height(
                    dimensionResource(
                        R.dimen.detail_content_padding_top
                    )
                )
            )
        } else {
            Text(
                text = stringResource(recommendation.offer),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.detail_content_padding_top),
                    bottom = dimensionResource(R.dimen.detail_expanded_body_spacing)
                )
            )
        }
        Row {
            Text(
                text = stringResource(recommendation.location),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(
                        start = dimensionResource(R.dimen.recommendation_location_padding)
                    )
                    .weight(1f)
            )
            Text(
                text = stringResource(recommendation.createdAt),
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(
                        end = dimensionResource(R.dimen.recommendation_time_padding)
                    )
                    .weight(1f)
            )
        }
        Text(
            text = stringResource(recommendation.body),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.detail_content_padding_top))
        )
    }
}

@Composable
private fun MyCityDetailsScreenHeader(
    recommendation: Recommendation,
    modifier: Modifier = Modifier
) {
    RecommendationSummaryImage(
        drawableResource = recommendation.image,
        description = stringResource(recommendation.offer)
    )
    Row(modifier = modifier) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(
                    horizontal = dimensionResource(R.dimen.detail_card_outer_padding_horizontal),
                    vertical = dimensionResource(R.dimen.topbar_padding_vertical)
                )
        ) {
            Text(
                text = stringResource(recommendation.offer),
                maxLines = 2,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}