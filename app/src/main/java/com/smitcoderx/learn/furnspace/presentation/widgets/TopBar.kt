package com.smitcoderx.learn.furnspace.presentation.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.smitcoderx.learn.furnspace.R
import com.smitcoderx.learn.furnspace.presentation.theme.Fonts.mulishFontFamily
import com.smitcoderx.learn.furnspace.presentation.theme.TextColor

@Composable
fun TopBar(navController: NavController, text: String, modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "back",
            modifier = Modifier
                .size(15.dp)
                .clickable { navController.popBackStack() })
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = text,
            color = TextColor,
            fontFamily = mulishFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
    }
}
