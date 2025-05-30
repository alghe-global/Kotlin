package com.example.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.network.AmphibiansPhoto
import com.example.amphibians.ui.theme.AmphibiansTheme

@Composable
fun HomeScreen(
    amphibiansUiState: AmphibiansUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when (amphibiansUiState) {
        is AmphibiansUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is AmphibiansUiState.Success -> AmphibiansPhotosColumnScreen(
            photos = amphibiansUiState.photos,
            modifier = modifier
        )
        is AmphibiansUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_connection_error),
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.loading_failed),
            modifier = Modifier.padding(16.dp)
        )
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun AmphibiansPhotosColumnScreen(
    photos: List<AmphibiansPhoto>,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(vertical = 4.dp),
        contentPadding = contentPadding
    ) {
        items(items = photos, key = { photo -> photo.name }) { photo ->
            AmphibiansPhotoCard(
                photo,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun AmphibiansPhotoCard(
    photo: AmphibiansPhoto,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = modifier
    ) {
        Column {
            Text(
                text = stringResource(R.string.photo_title, photo.name, photo.type),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(photo.imgSrc)
                    .crossfade(true)
                    .build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = stringResource(R.string.amphibians_photo),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = photo.description,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AmphibiansPhotosColumnScreenPreview() {
    AmphibiansTheme {
        val mockData = List(10) { AmphibiansPhoto("$it", "", "", "") }
        AmphibiansPhotosColumnScreen(mockData)
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    AmphibiansTheme {
        ErrorScreen({})
    }
}