package com.example.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanager.ui.theme.TaskManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TaskManagerImage(status = stringResource(R.string.task_status_text),
                        message = stringResource(R.string.task_message_text)
                    )
                }
            }
        }
    }
}

@Composable
fun TaskManagerText(status: String, message: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = status,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = modifier
                .padding(top=24.dp, bottom=8.dp)
        )
        Text(
            text = message,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = modifier
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun TaskManagerImage(status: String, message: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.ic_task_completed)
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
        )
        TaskManagerText(
            status = status,
            message = message,
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskManagerPreview() {
    TaskManagerTheme {
        TaskManagerImage(status = stringResource(R.string.task_status_text),
            message = stringResource(R.string.task_message_text)
        )
    }
}