package com.example.a10tips

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.a10tips.data.TipCard

@Composable
fun TipCardItem(
    card: TipCard,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .animateContentSize()
            .height(if (expanded) 460.dp else 336.dp)
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                expanded = !expanded
            }
    ) {
        Card(
            modifier = modifier
        ) {
            Column(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_medium))
            ) {
                Text(
                    text = stringResource(card.title),
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
                Image(
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.Crop,
                    painter = painterResource(card.imageResourceId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(R.dimen.image_height))
                )
                Text(
                    text = stringResource(card.description),
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(R.dimen.padding_medium),
                            bottom = dimensionResource(R.dimen.padding_small)
                        )
                )
            }
        }
    }
}