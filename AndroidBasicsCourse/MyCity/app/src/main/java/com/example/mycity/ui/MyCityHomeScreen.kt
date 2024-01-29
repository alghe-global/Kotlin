package com.example.mycity.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycity.data.Category
import com.example.mycity.data.Recommendation
import com.example.mycity.ui.theme.MyCityTheme
import com.example.mycity.ui.utils.MyCityContentType

@Composable
fun MyCityHomeScreen(
    contentType: MyCityContentType,
    myCityUiState: MyCityUiState,
    onCategoryCardPressed: (Category) -> Unit,
    onRecommendationCardPressed: (Recommendation) -> Unit,
    modifier: Modifier = Modifier
) {
    MyCityAppContent(
        contentType = contentType,
        myCityUiState = myCityUiState,
        onCategoryCardPressed = onCategoryCardPressed,
        onRecommendationCardPressed = onRecommendationCardPressed,
        modifier = modifier
    )
}

@Composable
private fun MyCityAppContent(
    contentType: MyCityContentType,
    myCityUiState: MyCityUiState,
    onCategoryCardPressed: (Category) -> Unit,
    onRecommendationCardPressed: (Recommendation) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            if (contentType == MyCityContentType.LIST_AND_DETAIL) {
                MyCityListAndDetailContent(
                    myCityUiState = myCityUiState,
                    onCategoryCardPressed = onCategoryCardPressed,
                    onRecommendationCardPressed = onRecommendationCardPressed,
                    modifier = Modifier.weight(1f)
                )
            } else {
                throw RuntimeException(
                    "else conditional hit when it should never have been reached."
                )
            }
        }
    }
}

@Preview(device = Devices.TABLET)
@Composable
fun MyCityAppContentTabletPreview() {
    val viewModel: MyCityViewModel = viewModel()
    val myCityUiState = viewModel.uiState.collectAsState().value
    Surface {
        MyCityTheme {
            MyCityAppContent(
                contentType = MyCityContentType.LIST_AND_DETAIL,
                myCityUiState = myCityUiState,
                onCategoryCardPressed = {},
                onRecommendationCardPressed = {}
            )
        }
    }
}

@Preview
@Composable
fun MyCityAppContentPreview() {
    val viewModel: MyCityViewModel = viewModel()
    val myCityUiState = viewModel.uiState.collectAsState().value
    Surface {
        MyCityTheme {
            MyCityAppContent(
                contentType = MyCityContentType.LIST_ONLY,
                myCityUiState = myCityUiState,
                onCategoryCardPressed = {},
                onRecommendationCardPressed = {}
            )
        }
    }
}