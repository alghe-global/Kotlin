package com.example.businesscard

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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PresentationApp()
                }
            }
        }
    }
}

@Composable
fun PresentationHeader(
    imagePainter: Painter,
    name: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .background(Color.White)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            Modifier.background(Color.DarkGray)
        ) {
            Image(
                painter = imagePainter,
                contentDescription = stringResource(
                    R.string.business_card_header_image_content_description
                ),
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            )
        }
        Text(
            text = name,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(bottom=4.dp)
        )
        Text(
            text = description,
            color = Color(0xFF3DDC84),
        )
    }
}

@Composable
fun PresentationTextWithIcon(
    imageVector: ImageVector,
    imageString: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start,
    ) {
        Row {
            Icon(imageVector, imageString)
            Spacer(modifier.width(16.dp))
            Text(
                text = text,
                color = Color(0xFF3DDC84),
            )
        }
    }
}

@Composable
fun PresentationFooter(modifier: Modifier = Modifier) {
    val appIcons = Icons.Rounded

    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(bottom=40.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            PresentationTextWithIcon(
                imageVector = appIcons.Call,
                imageString = stringResource(
                    R.string.presentation_footer_call_icon_text
                ),
                text = stringResource(
                    R.string.presentation_footer_phone_number
                )
            )
            Spacer(Modifier.height(10.dp))
            PresentationTextWithIcon(
                imageVector = appIcons.Share,
                imageString = stringResource(
                    R.string.presentation_footer_share_icon_text
                ),
                text = stringResource(
                    R.string.presentation_footer_social_media
                )
            )
            Spacer(Modifier.height(10.dp))
            PresentationTextWithIcon(
                imageVector = appIcons.Email,
                imageString = stringResource(
                    R.string.presentation_footer_email_icon_text
                ),
                text = stringResource(
                    R.string.presentation_footer_email_address
                )
            )
        }
    }
}

@Composable
fun PresentationApp() {
    PresentationHeader(
        imagePainter = painterResource(R.drawable.android_logo),
        name = stringResource(R.string.business_card_person_name),
        description = stringResource(R.string.business_card_person_title)
    )
    PresentationFooter()
}

@Preview(showBackground = true)
@Composable
fun PresentationAppPreview() {
    BusinessCardTheme {
        PresentationApp()
    }
}