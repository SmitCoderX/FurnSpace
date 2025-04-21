package com.smitcoderx.learn.furnspace.presentation.createAccount

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.smitcoderx.learn.furnspace.R
import com.smitcoderx.learn.furnspace.presentation.theme.Fonts
import com.smitcoderx.learn.furnspace.presentation.widgets.RenderButton
import com.smitcoderx.learn.furnspace.presentation.widgets.Space
import com.smitcoderx.learn.furnspace.presentation.widgets.shimmerEffect

@Composable
fun CreateAccount(rootNavigation: NavHostController) {
    val navHostController = rememberNavController()
    NavHost(navHostController, startDestination = Sheets.Onboard.name) {
        composable(Sheets.Onboard.name) { OnboardSheet(sheetNavigation = navHostController) }
        composable(Sheets.Signup.name) { RenderSignup(navHostController) {} }
        composable(Sheets.Login.name) { RenderSignIn(navHostController) { } }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = shimmerEffect(512.sp, true)),
    ) {
        Text(
            modifier = Modifier
                .padding(top = 140.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(R.string.app_name),
            textAlign = TextAlign.Center,
            fontFamily = Fonts.mulishFontFamily,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 40.sp,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OnboardSheet(sheetNavigation: NavHostController) {
    val lottieUrl by rememberLottieComposition(
        LottieCompositionSpec.Url(stringResource(R.string.account_lottie_url))
    )
    ModalBottomSheet(
        onDismissRequest = {},
        dragHandle = {}, sheetState = rememberModalBottomSheetState(
            confirmValueChange = { false }, skipPartiallyExpanded = true
        ),
        properties = ModalBottomSheetDefaults.properties(shouldDismissOnBackPress = false)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {
            LottieAnimation(
                modifier = Modifier.size(250.dp),
                composition = lottieUrl,
                iterations = LottieConstants.IterateForever,
            )
            Space(20.dp)
            Text(
                stringResource(R.string.onboard_message),
                textAlign = TextAlign.Center,
                fontFamily = Fonts.mulishFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                fontSize = 16.sp
            )
            Space(20.dp)
            RenderButton(text = "Login") { sheetNavigation.navigate(Sheets.Login.name) }
            Space(20.dp)
            RenderButton(text = "Signup") { sheetNavigation.navigate(Sheets.Signup.name) }
            Space(20.dp)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RenderSignup(sheetNavigation: NavHostController, onClick: () -> Unit) {
    ModalBottomSheet(
        onDismissRequest = {},
        dragHandle = {}, sheetState = rememberModalBottomSheetState(
            confirmValueChange = { false }, skipPartiallyExpanded = true
        ),
        properties = ModalBottomSheetDefaults.properties(shouldDismissOnBackPress = false)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.KeyboardArrowLeft,
                    contentDescription = "back",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable { sheetNavigation.popBackStack() }
                )
                Text(
                    text = "Signup", fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Fonts.mulishFontFamily,
                    textAlign = TextAlign.Center,
                    lineHeight = 30.sp
                )
            }
            Space(20.dp)
            RenderButton(
                modifier = Modifier.padding(bottom = 40.dp),
                stringResource(R.string.google_sign_up),
                R.drawable.ic_google,
            ) { onClick() }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RenderSignIn(sheetNavigation: NavHostController, onClick: () -> Unit) {
    ModalBottomSheet(
        onDismissRequest = {},
        dragHandle = {}, sheetState = rememberModalBottomSheetState(
            confirmValueChange = { false }, skipPartiallyExpanded = true
        ),
        properties = ModalBottomSheetDefaults.properties(shouldDismissOnBackPress = false)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.KeyboardArrowLeft,
                    contentDescription = "back",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable { sheetNavigation.popBackStack() }
                )
                Text(
                    text = "Login", fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Fonts.mulishFontFamily,
                    textAlign = TextAlign.Center,
                    lineHeight = 30.sp
                )
            }
            Space(20.dp)
            RenderButton(
                modifier = Modifier.padding(bottom = 40.dp),
                stringResource(R.string.google_sign_in),
                R.drawable.ic_google,
            ) { onClick() }
        }
    }
}

enum class Sheets {
    Onboard,
    Login,
    Signup
}
