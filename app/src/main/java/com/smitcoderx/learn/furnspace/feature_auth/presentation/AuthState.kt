package com.smitcoderx.learn.furnspace.feature_auth.presentation

import androidx.credentials.GetCredentialResponse

data class AuthState(
    val isSignInSuccessful: Boolean = false,
    val response: GetCredentialResponse? = null,
    val signInError: String? = null
)