package com.example.reply.test

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.reply.R
import com.example.reply.data.local.LocalEmailsDataProvider
import com.example.reply.ui.ReplyApp
import org.junit.Rule
import org.junit.Test

class ReplyAppStateRestorationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    @TestCompactWidth
    fun compactDevice_selectedEmailIsRetained_afterConfigurationChange() {
        /** Set up compact window */
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Compact)
        }

        /** Given third e-mail is displayed */
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).assertIsDisplayed()

        /** Open detailed page */
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].subject)
        ).performClick()

        /** Verify that the detailed screen for the correct e-mail is shown */
        composeTestRule.onNodeWithContentDescriptionForStringId(
            R.string.navigation_back
        ).assertExists()

        /** Simulate a config change */
        stateRestorationTester.emulateSavedInstanceStateRestore()

        /** Verify that it still shows the detailed screen for the same e-mail */
        composeTestRule.onNodeWithContentDescriptionForStringId(
            R.string.navigation_back
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).assertExists()
    }

    @Test
    @TestExpandedWidth
    fun expandedDevice_selectedEmailIsRetained_afterConfigurationChange() {
        /** Set up expanded window */
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Expanded)
        }

        /** Given third e-mail is displayed */
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).assertIsDisplayed()

        /** Select third e-mail */
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].subject)
        ).performClick()

        /** Verify that third e-mail is displayed on the details screen */
        composeTestRule.onNodeWithTagForStringId(R.string.details_screen).onChildren()
            .assertAny(hasAnyDescendant(hasText(
                composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)))
            )

        /** Simulate a config change */
        stateRestorationTester.emulateSavedInstanceStateRestore()

        /** Verify that third e-mail is displayed on the details screen */
        composeTestRule.onNodeWithTagForStringId(R.string.details_screen).onChildren()
            .assertAny(hasAnyDescendant(hasText(
                composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)))
            )
    }
}