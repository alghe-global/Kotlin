package com.example.mycity.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mycity.ui.utils.MyCityContentType

sealed class MyCityScreen(val route: String) {
    object Categories: MyCityScreen("Categories")
    object Recommendations: MyCityScreen("Recommendations")
    object Recommendation: MyCityScreen("Recommendation/{recommendationId}") {
        fun createRoute(recommendationId: Long) = "Recommendation/$recommendationId"
    }
}

@Composable
fun MyCityApp(
    windowSize: WindowWidthSizeClass,
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val viewModel: MyCityViewModel = viewModel()
    val myCityUiState = viewModel.uiState.collectAsState().value

    val contentType: MyCityContentType = when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            MyCityContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Medium -> {
            MyCityContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Expanded -> {
            MyCityContentType.LIST_AND_DETAIL
        }
        else -> {
            MyCityContentType.LIST_ONLY
        }
    }

    if (contentType != MyCityContentType.LIST_AND_DETAIL) {
        NavHost(
            navController = navController,
            startDestination = MyCityScreen.Categories.route
        ) {
            composable(route = MyCityScreen.Categories.route) {
                val categories = myCityUiState.categories

                MyCityCategoriesScreen(
                    categories = categories,
                    myCityUiState = myCityUiState,
                    navController = navController,
                    viewModel = viewModel
                )
            }

            composable(route = MyCityScreen.Recommendations.route) {
                val recommendations = myCityUiState.currentRecommendations

                MyCityRecommendationsScreen(
                    recommendations = recommendations,
                    myCityUiState = myCityUiState,
                    navController = navController
                )
            }

            composable(
                route = MyCityScreen.Recommendation.route,
                arguments = listOf(navArgument("recommendationId") {
                    type = NavType.IntType
                })
            ) { backStackEntry ->
                val recommendationId = backStackEntry.arguments?.getInt("recommendationId")
                val recommendation =
                    myCityUiState.allRecommendations[recommendationId!!]

                MyCityRecommendationDetailScreen(
                    recommendation = recommendation,
                    navController = navController
                )
            }
        }
    } else {
        MyCityHomeScreen(
            contentType = contentType,
            myCityUiState = myCityUiState,
            onCategoryCardPressed = {
                viewModel.updateRecommendations(category = it)
                viewModel.updateDetailsScreenStates(
                    category = it,
                    recommendation = myCityUiState.currentRecommendations[0]
                )
            },
            onRecommendationCardPressed = {
                viewModel.updateDetailsScreenStates(
                    category = myCityUiState.currentSelectedCategory,
                    recommendation = it
                )
            }
        )
    }
}