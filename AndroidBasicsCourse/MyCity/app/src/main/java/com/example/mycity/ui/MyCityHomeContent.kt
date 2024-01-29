package com.example.mycity.ui

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.mycity.R
import com.example.mycity.data.Category
import com.example.mycity.data.Recommendation

@Composable
fun MyCityListAndDetailContent(
    myCityUiState: MyCityUiState,
    onCategoryCardPressed: (Category) -> Unit,
    onRecommendationCardPressed: (Recommendation) -> Unit,
    modifier: Modifier = Modifier
) {
    val categories = myCityUiState.categories
    val recommendations = myCityUiState.currentRecommendations

    Row(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
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
                    selected = myCityUiState.currentSelectedCategory.id == category.id,
                    onCardClick = {
                        onCategoryCardPressed(category)
                    }
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(
                    end = dimensionResource(R.dimen.list_and_detail_list_padding_end),
                    top = dimensionResource(R.dimen.list_and_detail_list_padding_top)
                ),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen.recommendation_list_padding)
            )
        ) {
            items(recommendations, key = {
                recommendation -> recommendation.id
            }) { recommendation ->
                MyCityRecommendationListItem(
                    recommendation = recommendation,
                    selected = myCityUiState.currentSelectedRecommendation.id == recommendation.id,
                    onCardClick = {
                        onRecommendationCardPressed(recommendation)
                    }
                )
            }
        }
        val activity = LocalContext.current as Activity
        MyCityDetailsScreen(
            myCityUiState = myCityUiState,
            isFullScreen = false,
            onBackPressed = { activity.finish() },
            modifier = Modifier.weight(1f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityRecommendationListItem(
    recommendation: Recommendation,
    selected: Boolean,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = if (selected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.secondaryContainer
        ),
        onClick = onCardClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.recommendation_type_padding))
        ) {
            Image(
                painterResource(recommendation.image),
                contentDescription = null
            )
            Text(
                text = stringResource(recommendation.offer),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.recommendation_list_padding),
                    bottom = dimensionResource(R.dimen.recommendation_list_padding)
                )
            )
            Text(
                text = stringResource(recommendation.body),
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    bottom = dimensionResource(R.dimen.recommendation_list_padding)
                )
            )
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
                text = recommendation.type.name,
                textAlign = TextAlign.End,
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.recommendation_list_padding),
                    bottom = dimensionResource(R.dimen.recommendation_list_padding)
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityCategoryListItem(
    category: Category,
    selected: Boolean,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = if (selected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.secondaryContainer
        ),
        onClick = onCardClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.recommendation_type_padding))
        ) {
            Image(
                painterResource(category.image),
                contentDescription = null
            )
            Text(
                text = stringResource(category.name),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.recommendation_list_padding),
                    bottom = dimensionResource(R.dimen.recommendation_list_padding)
                )
            )
        }
    }
}

@Composable
fun MyCityLogo(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.app_name),
        textAlign = TextAlign.Start,
        modifier = modifier
    )
}

@Composable
private fun MyCityHomeTopBar(modifier: Modifier = Modifier) {
    MyCityLogo(
        modifier = Modifier
            .size(dimensionResource(R.dimen.topbar_logo_size))
            .padding(start = dimensionResource(R.dimen.topbar_logo_padding_start))
    )
}

@Composable
fun RecommendationSummaryImage(
    @DrawableRes drawableResource: Int,
    description: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            modifier = Modifier,
            painter = painterResource(drawableResource),
            contentDescription = description
        )
    }
}