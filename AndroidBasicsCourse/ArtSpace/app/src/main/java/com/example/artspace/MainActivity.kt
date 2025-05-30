package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout() {
    var currentStep by remember { mutableIntStateOf(1) }

    /**
     * Display a background box.
     */
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .size(width = 350.dp, height = 350.dp)
                .shadow(5.dp)
        ) {

        }
    }
    when (currentStep) {
        1 -> {
            ImageWithDescription(
                drawableResourceId = R.drawable.one,
                contentDescriptionResourceId = R.string.first_image,
                titleResourceId = R.string.first_image,
                authorResourceId = R.string.first_image_author,
                yearResourceId = R.string.first_image_year
            )
        }
        2 -> {
            ImageWithDescription(
                drawableResourceId = R.drawable.two,
                contentDescriptionResourceId = R.string.second_image,
                titleResourceId = R.string.second_image,
                authorResourceId = R.string.second_image_author,
                yearResourceId = R.string.second_image_year
            )
        }
        3 -> {
            ImageWithDescription(
                drawableResourceId = R.drawable.three,
                contentDescriptionResourceId = R.string.third_image,
                titleResourceId = R.string.third_image,
                authorResourceId = R.string.third_image_author,
                yearResourceId = R.string.third_image_year
            )
        }
        4 -> {
            ImageWithDescription(
                drawableResourceId = R.drawable.four,
                contentDescriptionResourceId = R.string.fourth_image,
                titleResourceId = R.string.fourth_image,
                authorResourceId = R.string.fourth_image_author,
                yearResourceId = R.string.fourth_image_year
            )
        }
        5 -> {
            ImageWithDescription(
                drawableResourceId = R.drawable.five,
                contentDescriptionResourceId = R.string.fifth_image,
                titleResourceId = R.string.fifth_image,
                authorResourceId = R.string.fifth_image_author,
                yearResourceId = R.string.fifth_image_year
            )
        }
        6 -> {
            ImageWithDescription(
                drawableResourceId = R.drawable.six,
                contentDescriptionResourceId = R.string.sixth_image,
                titleResourceId = R.string.sixth_image,
                authorResourceId = R.string.sixth_image_author,
                yearResourceId = R.string.sixth_image_year
            )
        }
        7 -> {
            ImageWithDescription(
                drawableResourceId = R.drawable.seven,
                contentDescriptionResourceId = R.string.seventh_image,
                titleResourceId = R.string.seventh_image,
                authorResourceId = R.string.seventh_image_author,
                yearResourceId = R.string.seventh_image_year
            )
        }
        8 -> {
            ImageWithDescription(
                drawableResourceId = R.drawable.eight,
                contentDescriptionResourceId = R.string.eighth_image,
                titleResourceId = R.string.eighth_image,
                authorResourceId = R.string.eighth_image_author,
                yearResourceId = R.string.eighth_image_year
            )
        }
        9 -> {
            ImageWithDescription(
                drawableResourceId = R.drawable.nine,
                contentDescriptionResourceId = R.string.ninth_image,
                titleResourceId = R.string.ninth_image,
                authorResourceId = R.string.ninth_image_author,
                yearResourceId = R.string.ninth_image_year
            )
        }
    }

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp)
    ) {
        Button(
            onClick = {
                if (currentStep < 9) {
                    currentStep++
                }
            },
            shape = RoundedCornerShape(20),
            modifier = Modifier
                .size(105.dp, 60.dp)
                .padding(bottom = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.next_button)
            )
        }
    }
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 20.dp)
    ) {
            Button(
                onClick = {
                    if (currentStep > 1) {
                        currentStep--
                    }
                },
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .size(105.dp, 60.dp)
                    .padding(bottom = 20.dp)
            ) {
                Text(text = stringResource(R.string.previous_button))
            }
    }
}

@Composable
fun ImageWithDescription(
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    titleResourceId: Int,
    authorResourceId: Int,
    yearResourceId: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(drawableResourceId),
            contentDescription = stringResource(contentDescriptionResourceId),
            modifier = Modifier
                .size(300.dp)
                .clip(RectangleShape)
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .padding(start = 20.dp, bottom = 90.dp, end = 20.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(color = Color.LightGray.copy(alpha = 0.4f))
                    .size(width = 345.dp, height = 100.dp)
            ) {
                Column {
                    Text(
                        text = stringResource(titleResourceId),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .padding(start = 10.dp, top = 10.dp)
                    )
                    Row(
                        modifier = Modifier
                    ) {
                        Text(
                            text = stringResource(authorResourceId),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 10.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = stringResource(yearResourceId),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceLayoutPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}