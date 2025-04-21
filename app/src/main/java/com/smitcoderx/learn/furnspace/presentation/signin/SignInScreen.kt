package com.smitcoderx.learn.furnspace.presentation.signin

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(rootNavigation: NavHostController) {
    ModalBottomSheet(onDismissRequest = {}) {
        Text("Signin")
        Button(onClick = { rootNavigation.popBackStack() }) {
            Text("Back")
        }
    }
}
