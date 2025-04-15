package com.smitcoderx.learn.furnspace.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smitcoderx.learn.furnspace.presentation.createAccount.CreateAccount
import com.smitcoderx.learn.furnspace.presentation.onboarding.OnboardingScreen
import com.smitcoderx.learn.furnspace.presentation.privacy.PrivacyScreen
import com.smitcoderx.learn.furnspace.presentation.signin.SignInScreen

@Composable
fun RootNavigation() {
    val navController = rememberNavController()
    var startDestination = Screens.Onboarding.title
    NavHost(navController, startDestination = startDestination) {
        composable(Screens.Onboarding.title) {
            OnboardingScreen(navController)
        }
        composable(Screens.SignIn.title) {
            SignInScreen()
        }
        composable(Screens.Privacy.title) {
            PrivacyScreen()
        }
        composable(Screens.CreateAccount.title) {
            CreateAccount()
        }
    }
}
