package com.smitcoderx.learn.furnspace.presentation.widgets

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.sp
import com.smitcoderx.learn.furnspace.presentation.theme.AccentColor
import com.smitcoderx.learn.furnspace.presentation.theme.ExtraLightAccentColor
import com.smitcoderx.learn.furnspace.presentation.theme.LightAccentColor

@Composable
fun Brush.ShimmerEffect(modifier: Modifier = Modifier) {
    val gradientList = listOf(ExtraLightAccentColor, LightAccentColor, AccentColor)
    val currentFontSizePx = with(LocalDensity.current) { 50.sp.toPx() }
    val currentFontSizeDoublePx = currentFontSizePx * 2

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = currentFontSizeDoublePx,
        animationSpec = infiniteRepeatable(tween(1200, easing = LinearEasing)), label = ""
    )
    val brush = Brush.linearGradient(
        gradientList,
        start = Offset(offset, offset),
        end = Offset(offset + currentFontSizePx, offset + currentFontSizePx),
        tileMode = TileMode.Mirror
    )
}
