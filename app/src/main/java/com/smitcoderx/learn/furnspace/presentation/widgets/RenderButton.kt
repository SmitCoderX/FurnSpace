package com.smitcoderx.learn.furnspace.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smitcoderx.learn.furnspace.presentation.theme.AccentColor
import com.smitcoderx.learn.furnspace.presentation.theme.Fonts

@Composable
fun RenderButton(
    modifier: Modifier = Modifier,
    text: String,
    drawable: Int? = null,
    bgColor: Color = AccentColor,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp)
            .background(bgColor, RoundedCornerShape(40.dp)),
        onClick = onClick
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text,
                color = Color.White,
                fontFamily = Fonts.mulishFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            if (drawable == null) return@Button
            Space(20.dp)
            AsyncImagePainter(drawable = drawable, modifier = Modifier.size(20.dp))
        }
    }
}
