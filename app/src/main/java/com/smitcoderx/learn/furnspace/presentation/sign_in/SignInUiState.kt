package com.smitcoderx.learn.furnspace.presentation.sign_in

data class SignInUiState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null,
)