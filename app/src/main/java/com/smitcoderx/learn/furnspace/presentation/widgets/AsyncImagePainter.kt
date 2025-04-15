package com.smitcoderx.learn.furnspace.presentation.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun AsyncImagePainter(
    modifier: Modifier = Modifier,
    drawable: Int,
    contentDescription: String? = null,
    quality: FilterQuality = FilterQuality.High,
    contentScale: ContentScale = ContentScale.Crop
) {
    AsyncImage(
        modifier = modifier,
        model = drawable,
        contentDescription = contentDescription,
        filterQuality = quality,
        contentScale = contentScale
    )
}
