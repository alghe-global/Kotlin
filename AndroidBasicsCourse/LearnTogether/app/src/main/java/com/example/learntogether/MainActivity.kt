package com.example.learntogether

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learntogether.ui.theme.LearnTogetherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnTogetherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArticleImage(title = stringResource(R.string.title_text),
                        summary = stringResource(R.string.summary_text),
                        content = stringResource(R.string.article_text))
                }
            }
        }
    }
}

@Composable
fun ArticleText(title: String, summary: String, content: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            modifier = modifier
                .padding(16.dp)
        )
        Text(
            text = summary,
            textAlign = TextAlign.Justify,
            modifier = modifier
                .padding(start=16.dp, end=16.dp)
        )
        Text(
            text = content,
            textAlign = TextAlign.Justify,
            modifier = modifier
                .padding(16.dp)
        )
    }
}

@Composable
fun ArticleImage(title: String, summary: String, content: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.bg_compose_background)
    Column(Modifier.fillMaxSize()) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = modifier
        )
        ArticleText(
            title = title,
            summary = summary,
            content = content,
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArticlePreview() {
    LearnTogetherTheme {
        ArticleImage(title = stringResource(R.string.title_text),
            summary = stringResource(R.string.summary_text),
            content = stringResource(R.string.article_text))
    }
}