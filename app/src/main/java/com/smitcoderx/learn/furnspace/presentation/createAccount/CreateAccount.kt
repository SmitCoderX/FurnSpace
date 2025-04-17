package com.smitcoderx.learn.furnspace.presentation.createAccount

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.smitcoderx.learn.furnspace.R
import com.smitcoderx.learn.furnspace.presentation.theme.Fonts
import com.smitcoderx.learn.furnspace.presentation.widgets.shimmerEffect

@Composable
fun CreateAccount() {
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = buildAnnotatedString {
                append(stringResource(R.string.create_acc))
                pushStyle(SpanStyle(brush = shimmerEffect()))
                append(stringResource(R.string.create_acc_space))
            },
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Fonts.mulishFontFamily,
            textAlign = TextAlign.Center,
            lineHeight = 30.sp
        )
    }
}
