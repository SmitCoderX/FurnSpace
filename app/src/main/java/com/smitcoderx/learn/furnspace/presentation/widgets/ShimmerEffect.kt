package com.smitcoderx.learn.furnspace.presentation.widgets

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.smitcoderx.learn.furnspace.presentation.theme.AccentColor
import com.smitcoderx.learn.furnspace.presentation.theme.ExtraLightAccentColor
import com.smitcoderx.learn.furnspace.presentation.theme.LightAccentColor

@Composable
fun shimmerEffect(size: TextUnit = 50.sp, screen: Boolean = false): Brush {
    val gradientList =
        if (screen)
            mutableListOf(
                AccentColor.copy(alpha = 0.7f),
                AccentColor.copy(alpha = 0.8f),
                AccentColor.copy(alpha = 0.9f)
            )
        else
            mutableListOf(ExtraLightAccentColor, LightAccentColor, AccentColor)
    val currentFontSizePx = with(LocalDensity.current) { size.toPx() }
    val currentFontSizeDoublePx = currentFontSizePx * 2

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = currentFontSizeDoublePx,
        animationSpec = infiniteRepeatable(
            tween(
                if (!screen) 1200 else 2500,
                easing = if (!screen) LinearEasing else FastOutSlowInEasing
            )
        ),
        label = "Shimmer"
    )
    val brush = Brush.linearGradient(
        gradientList,
        start = Offset(offset, offset),
        end = Offset(offset + currentFontSizePx, offset + currentFontSizePx),
        tileMode = TileMode.Mirror
    )
    return brush
}
