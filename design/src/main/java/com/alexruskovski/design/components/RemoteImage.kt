package com.alexruskovski.design.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.alexruskovski.design.R

@Composable
fun RemoteImage(
    imageUrl: String,
    contentDescription: String? = null,
    cornerShapeRadius: Dp = 0.dp,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerShapeRadius))
    ) {
        val context = LocalContext.current
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(imageUrl)
                .crossfade(true)
                .scale(Scale.FILL)
                .placeholder(R.drawable.gym_shark_icon)
                .fallback(R.drawable.gym_shark_icon)
                .error(R.drawable.gym_shark_icon)
                .build(),
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
        )
    }
}
