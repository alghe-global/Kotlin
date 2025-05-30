package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuadrantCompose()
                }
            }
        }
    }
}

@Composable
fun QuadrantText(
    title: String,
    text: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Justify,
            modifier = modifier
                .padding(top = 140.dp, bottom = 16.dp)
        )
        Text(
            text = text,
            textAlign = TextAlign.Justify,
            modifier = modifier
        )
    }
}

@Composable
fun QuadrantCompose() {
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.weight(1f)) {
            QuadrantText(
                title = stringResource(R.string.quadrant_one_title),
                text = stringResource(R.string.quadrant_one_text),
                backgroundColor = Color(0xFFEADDFF),
                modifier = Modifier.weight(1f)
            )
            QuadrantText(
                title = stringResource(R.string.quadrant_two_title),
                text = stringResource(R.string.quadrant_two_text),
                backgroundColor = Color(0xFFD0BCFF),
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            QuadrantText(
                    title = stringResource(R.string.quadrant_three_title),
                    text = stringResource(R.string.quadrant_three_text),
                    backgroundColor = Color(0xFFB69DF8),
                    modifier = Modifier.weight(1f)
                )
            QuadrantText(
                title = stringResource(R.string.quadrant_four_title),
                text = stringResource(R.string.quadrant_four_text),
                backgroundColor = Color(0xFFF6EDFF),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuadrantPreview() {
    ComposeQuadrantTheme {
        QuadrantCompose()
    }
}