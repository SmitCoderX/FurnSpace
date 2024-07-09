package com.smitcoderx.learn.furnspace.feature_privacy.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.smitcoderx.learn.furnspace.R
import com.smitcoderx.learn.furnspace.presentation.components.TopBar
import com.smitcoderx.learn.furnspace.core.mulishFontFamily
import com.smitcoderx.learn.furnspace.ui.theme.TextColor

@Composable
fun PrivacyScreen(navController: NavController, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column {
            TopBar(navController = navController, text = stringResource(id = R.string.privacy))
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.lorem),
                color = TextColor,
                fontSize = 15.sp,
                textAlign = TextAlign.Start,
                fontFamily = mulishFontFamily,
                fontWeight = FontWeight.Normal,
            )
        }
    }
}