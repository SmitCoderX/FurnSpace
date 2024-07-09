package com.smitcoderx.learn.furnspace.feature_create.presentation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.smitcoderx.learn.furnspace.R
import com.smitcoderx.learn.furnspace.core.Screens
import com.smitcoderx.learn.furnspace.core.mulishFontFamily
import com.smitcoderx.learn.furnspace.presentation.components.EditText
import com.smitcoderx.learn.furnspace.presentation.components.TopBar
import com.smitcoderx.learn.furnspace.ui.theme.AccentColor
import com.smitcoderx.learn.furnspace.ui.theme.ButtonTextColor
import com.smitcoderx.learn.furnspace.ui.theme.ExtraLightAccentColor
import com.smitcoderx.learn.furnspace.ui.theme.LightAccentColor
import com.smitcoderx.learn.furnspace.ui.theme.TextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen(navController: NavController, modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val gradientList = listOf(ExtraLightAccentColor, LightAccentColor, AccentColor)
    val currentFontSizePx = with(LocalDensity.current) { 50.sp.toPx() }
    val currentFontSizeDoublePx = currentFontSizePx * 2

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = currentFontSizeDoublePx,
        animationSpec = infiniteRepeatable(tween(1200, easing = LinearEasing)),
        label = ""
    )
    val brush = Brush.linearGradient(
        gradientList,
        start = Offset(offset, offset),
        end = Offset(offset + currentFontSizePx, offset + currentFontSizePx),
        tileMode = TileMode.Mirror
    )


    val interactionSource = remember { MutableInteractionSource() }
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Box(
        modifier = modifier.fillMaxSize()
    ) {


        Column(modifier = Modifier.padding(16.dp)) {
            TopBar(
                navController = navController,
                text = stringResource(id = R.string.create_acc)
            )
            Text(
                text = stringResource(id = R.string.app_name),
                style = TextStyle(brush = brush),
                fontFamily = mulishFontFamily,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp)
            )
            EditText(
                value = userName,
                onValueChange = {
                    userName = it
                },
                modifier = Modifier.padding(top = 20.dp),
                hint = stringResource(id = R.string.enter_username),
                interactionSource = interactionSource,
                inputType = KeyboardType.Text
            )
            EditText(
                value = email,
                onValueChange = {
                    email = it
                },
                modifier = Modifier.padding(top = 10.dp),
                hint = stringResource(id = R.string.enter_email),
                interactionSource = interactionSource,
                inputType = KeyboardType.Email
            )

            EditText(
                value = password,
                onValueChange = {
                    password = it
                },
                modifier = Modifier.padding(top = 10.dp),
                hint = stringResource(id = R.string.enter_pass),
                interactionSource = interactionSource,
                isPasswordField = true,
                inputType = KeyboardType.Password
            )

            Button(modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 30.dp)
                .background(AccentColor, shape = CircleShape), onClick = {
                navController.navigate(Screens.CREATE)
            }) {
                Text(
                    stringResource(id = R.string.sign_up),
                    fontFamily = mulishFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = ButtonTextColor,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            }


            Text(
                stringResource(id = R.string.already_account),
                fontFamily = mulishFontFamily,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                color = TextColor,
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp),
            )

            Text(
                stringResource(id = R.string.or_sign_in),
                fontFamily = mulishFontFamily,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                color = AccentColor,
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp),
            )
            
            Row {

            }

        }

        Image(
            painter = painterResource(id = R.drawable.account_bg),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.BottomStart)
                .size(300.dp)
                .offset(x = (-50).dp, y = 45.dp),
            contentScale = ContentScale.Crop
        )
    }

}