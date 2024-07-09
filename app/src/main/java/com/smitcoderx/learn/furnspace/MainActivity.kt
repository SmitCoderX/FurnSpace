package com.smitcoderx.learn.furnspace

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smitcoderx.learn.furnspace.core.Screens
import com.smitcoderx.learn.furnspace.feature_auth.presentation.SignInScreen
import com.smitcoderx.learn.furnspace.feature_create.presentation.CreateAccountScreen
import com.smitcoderx.learn.furnspace.feature_privacy.presentation.PrivacyScreen
import com.smitcoderx.learn.furnspace.ui.theme.FurnSpaceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FurnSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screens.SIGN_IN) {
                        composable(Screens.SIGN_IN) {
                            SignInScreen(navController)
                        }

                        composable(Screens.PRIVACY) {
                            PrivacyScreen(navController)
                        }

                        composable(Screens.CREATE) {
                            CreateAccountScreen(navController)
                        }
                    }
                }
            }
        }
    }
}