package com.smitcoderx.learn.furnspace.presentation.createAccount

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.smitcoderx.learn.furnspace.R
import com.smitcoderx.learn.furnspace.presentation.theme.Fonts
import com.smitcoderx.learn.furnspace.presentation.theme.LightAccentColor
import com.smitcoderx.learn.furnspace.presentation.widgets.RenderButton
import com.smitcoderx.learn.furnspace.presentation.widgets.Space
import com.smitcoderx.learn.furnspace.presentation.widgets.shimmerEffect

@Composable
fun CreateAccount(rootNavigation: NavHostController) {
    var accountState by remember { mutableStateOf(AccountState.Signup) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = shimmerEffect(512.sp, true))
    ) {
        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 20.dp)
                .padding(bottom = 100.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 100.dp)
        ) {
            if (accountState.name == AccountState.Signup.name) RenderSignup { } else RenderSignIn { }
        }
        RenderButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 80.dp),
            bgColor = LightAccentColor,
            text = if (accountState.name == AccountState.Signup.name) stringResource(R.string.already_account)
            else stringResource(R.string.create_account),
        ) {
            accountState =
                if (accountState.name == AccountState.Signup.name) AccountState.SignIn
                else AccountState.Signup
        }
    }
}

@Composable
private fun RenderSignup(onClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(20.dp)
    ) {
        Text(
            "Sign up now", fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Fonts.mulishFontFamily,
            textAlign = TextAlign.Center,
            lineHeight = 30.sp
        )
        Space(20.dp)
        RenderButton(
            modifier = Modifier.padding(bottom = 40.dp),
            stringResource(R.string.google_sign_up),
            R.drawable.ic_google,
        ) { onClick() }
    }
}

@Composable
private fun RenderSignIn(onClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(20.dp)
    ) {
        Text(
            "Sign in now", fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Fonts.mulishFontFamily,
            textAlign = TextAlign.Center,
            lineHeight = 30.sp
        )
        Space(20.dp)
        RenderButton(
            modifier = Modifier.padding(bottom = 40.dp),
            stringResource(R.string.google_sign_in),
            R.drawable.ic_google,
        ) { onClick() }
    }
}

enum class AccountState {
    Signup,
    SignIn
}
