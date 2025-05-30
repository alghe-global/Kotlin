package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.cupcake.CupcakeApp
import com.example.cupcake.CupcakeScreen
import com.example.cupcake.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CupcakeScreenNavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupCupcakeNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            CupcakeApp(navController = navController)
        }
    }

    private fun getFormattedDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, 1)
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        return formatter.format(calendar.time)
    }

    private fun performNavigateUp() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).performClick()
    }

    private fun performCancelClick() {
        composeTestRule.onNodeWithStringId(R.string.cancel)
            .performClick()
    }

    private fun performNextClick() {
        composeTestRule.onNodeWithStringId(R.string.next)
            .performClick()
    }

    private fun navigateToFlavorScreen() {
        composeTestRule.onNodeWithStringId(R.string.one_cupcake)
            .performClick()
        composeTestRule.onNodeWithStringId(R.string.chocolate)
            .performClick()
    }

    private fun navigateToStartFromFlavorScreenByNavigatingUp() {
        navigateToFlavorScreen()
        performNavigateUp()
    }

    private fun navigateToStartFromFlavorScreenByCancelling() {
        navigateToFlavorScreen()
        performCancelClick()
    }

    private fun navigateToPickupScreen() {
        navigateToFlavorScreen()
        performNextClick()
    }

    private fun navigateToFlavorScreenByNavigationUp() {
        navigateToPickupScreen()
        performNavigateUp()
    }

    private fun navigateToStartFromPickupScreenByCancelling() {
        navigateToPickupScreen()
        performCancelClick()
    }

    private fun navigateToSummaryScreen() {
        navigateToPickupScreen()
        composeTestRule.onNodeWithText(getFormattedDate())
            .performClick()
        performNextClick()
    }

    private fun navigateToPickupScreenByNavigationUp() {
        navigateToSummaryScreen()
        performNavigateUp()
    }

    private fun navigateToStartFromSummaryScreenByCancelling() {
        navigateToSummaryScreen()
        performCancelClick()
    }

    @Test
    fun cupcakeNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_verifyBackNavigationNotShownOnStartOrderScreen() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    @Test
    fun cupcakeNavHost_clickOneCupcake_navigatesToSelectFlavorScreen() {
        navigateToFlavorScreen()
        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
    }

    @Test
    fun cupcakeNavHost_clickUpButtonFromFlavorScreen_navigatesToStartScreen() {
        navigateToStartFromFlavorScreenByNavigatingUp()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_clickCancelButtonFromFlavorScreen_navigatesToStartScreen() {
        navigateToStartFromFlavorScreenByCancelling()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_navigateToPickupScreen() {
        navigateToPickupScreen()
        navController.assertCurrentRouteName(CupcakeScreen.Pickup.name)
    }

    @Test
    fun cupcakeNavHost_clickUpButtonFromPickupScreen_navigatesToFlavorScreen() {
        navigateToFlavorScreenByNavigationUp()
        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
    }

    @Test
    fun cupcakeNavHost_clickCancelButtonFromPickupScreen_navigatesToStartScreen() {
        navigateToStartFromPickupScreenByCancelling()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_navigateToSummaryScreen() {
        navigateToSummaryScreen()
        navController.assertCurrentRouteName(CupcakeScreen.Summary.name)
    }

    @Test
    fun cupcakeNavHost_clickUpButtonFromSummaryScreen_navigatesToPickupScreen() {
        navigateToPickupScreenByNavigationUp()
        navController.assertCurrentRouteName(CupcakeScreen.Pickup.name)
    }

    @Test
    fun cupcakeNavHost_clickCancelButtonFromSummaryScreen_navigatesToStartScreen() {
        navigateToStartFromSummaryScreenByCancelling()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }
}