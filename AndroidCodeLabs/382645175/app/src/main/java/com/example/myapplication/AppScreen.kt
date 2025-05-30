package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.lang.Thread.sleep

@OptIn(ExperimentalMaterial3Api::class)
@androidx.compose.runtime.Composable
fun AppScreen(
    modifier: Modifier = Modifier,
    appViewModel: AppViewModel
) {
    val pullToRefreshState = rememberPullToRefreshState()

    val isRefreshing = appViewModel.isRefreshing.value

    val onRefresh: () -> Unit = {
        /** Signal we started refresh */
        appViewModel.setIsRefreshing(true)

        /** Fake work */
        sleep(2000L)

        /** Signal isRefreshing is off */
        appViewModel.setIsRefreshing(false)
    }

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        PullToRefreshBox(
            modifier = Modifier.padding(innerPadding),
            state = pullToRefreshState,
            isRefreshing = isRefreshing,
            onRefresh = onRefresh
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                item {
                    if (isRefreshing) {
                        CircularProgressIndicator()
                    } else {
                        Text("Not refreshing. Main content.")
                    }
                }
            }
        }
    }
}