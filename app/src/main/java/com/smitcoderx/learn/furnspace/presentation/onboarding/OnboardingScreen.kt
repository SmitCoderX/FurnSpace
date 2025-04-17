package com.smitcoderx.learn.furnspace.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.smitcoderx.learn.furnspace.R
import com.smitcoderx.learn.furnspace.core.Screens
import com.smitcoderx.learn.furnspace.presentation.theme.AccentColor
import com.smitcoderx.learn.furnspace.presentation.theme.Fonts
import com.smitcoderx.learn.furnspace.presentation.widgets.AsyncImagePainter
import com.smitcoderx.learn.furnspace.presentation.widgets.Space
import com.smitcoderx.learn.furnspace.presentation.widgets.shimmerEffect

@Composable
fun OnboardingScreen(rootNavigation: NavHostController) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        AsyncImagePainter(
            modifier = Modifier.size(320.dp),
            R.drawable.ic_onboarding,
            contentScale = ContentScale.Inside
        )
        Space(80.dp)
        Text(
            buildAnnotatedString {
                append(stringResource(R.string.login_text))
                pushStyle(SpanStyle(brush = shimmerEffect()))
                append(stringResource(R.string.login_text_special))
            },
            fontFamily = Fonts.mulishFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 30.sp
        )
        Space(80.dp)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            Text(
                stringResource(R.string.privacy),
                fontFamily = Fonts.mulishFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp, modifier = Modifier.clickable(onClick = {
                    rootNavigation.navigate(Screens.PRIVACY)
                })
            )
            Space(20.dp)
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .background(AccentColor, RoundedCornerShape(40.dp)),
                onClick = { rootNavigation.navigate(Screens.CREATE) }
            ) {
                Text(
                    stringResource(R.string.login_button_text),
                    color = Color.White,
                    fontFamily = Fonts.mulishFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }
        }
    }
}
