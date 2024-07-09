package com.smitcoderx.learn.furnspace.feature_auth.presentation

import android.os.Build.VERSION.SDK_INT
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.smitcoderx.learn.furnspace.R
import com.smitcoderx.learn.furnspace.core.Screens
import com.smitcoderx.learn.furnspace.core.mulishFontFamily
import com.smitcoderx.learn.furnspace.ui.theme.AccentColor
import com.smitcoderx.learn.furnspace.ui.theme.ButtonTextColor
import com.smitcoderx.learn.furnspace.ui.theme.ExtraLightAccentColor
import com.smitcoderx.learn.furnspace.ui.theme.LightAccentColor
import com.smitcoderx.learn.furnspace.ui.theme.Purple40
import com.smitcoderx.learn.furnspace.ui.theme.Purple80
import com.smitcoderx.learn.furnspace.ui.theme.TextColor

@Composable
fun SignInScreen(
    navController: NavController
) {
    val context = LocalContext.current
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
    val imageLoader = ImageLoader.Builder(context).components {
        if (SDK_INT >= 28) {
            add(ImageDecoderDecoder.Factory())
        } else {
            add(GifDecoder.Factory())
        }
    }.build()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(context).data(data = R.drawable.conversation)
                        .apply(block = {
                            size(Size.ORIGINAL)
                        }).build(), imageLoader = imageLoader
                ), contentDescription = null, modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .size(400.dp)
            )
            Text(textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 15.dp),
                lineHeight = 30.sp,
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = TextColor,
                            fontFamily = mulishFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    ) {
                        append(stringResource(id = R.string.login_text))
                    }

                    withStyle(
                        style = SpanStyle(
                            brush = brush,
                            fontFamily = mulishFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    ) {
                        append(" Space.")
                    }
                })

            Spacer(modifier = Modifier.height(170.dp))
            Text(text = stringResource(id = R.string.privacy),
                color = TextColor,
                fontSize = 14.sp,
                fontFamily = mulishFontFamily,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.clickable {
                    navController.navigate(Screens.PRIVACY)
                })

            Spacer(modifier = Modifier.height(20.dp))
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .background(AccentColor, shape = CircleShape), onClick = {
                navController.navigate(Screens.CREATE)
            }) {
                Text(
                    stringResource(id = R.string.login_button_text),
                    fontFamily = mulishFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = ButtonTextColor,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            }
        }
    }
}
