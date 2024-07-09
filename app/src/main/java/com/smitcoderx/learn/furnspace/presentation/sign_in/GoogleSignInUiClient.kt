package com.smitcoderx.learn.furnspace.presentation.sign_in

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.smitcoderx.learn.furnspace.BuildConfig
import com.smitcoderx.learn.furnspace.feature_auth.domain.data.SignInResult
import com.smitcoderx.learn.furnspace.feature_auth.domain.data.UserData
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class GoogleSignInUiClient(
    private val context: Context,
    private val credentialManager: CredentialManager
) {

    private val auth = Firebase.auth

    suspend fun signIn(): GetCredentialResponse? {
        val result = try {
            credentialManager.getCredential(
                request = createSignInRequest(),
                context = context,
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result
    }

    suspend fun signInWithCredential(googleIdToken: GoogleIdTokenCredential): SignInResult {

//        val googleIdToken = GoogleIdTokenCredential.createFrom(credential)
        val googleCredential = GoogleAuthProvider.getCredential(googleIdToken.idToken, null)
        return try {
            val user = auth.signInWithCredential(googleCredential).await().user
            Log.d("TAG", "signInWithCredential: ${user?.displayName}")
            SignInResult(
                dataModel = user?.run {
                    UserData(
                        id = uid,
                        name = displayName,
                        email = email,
                        profileUrl = photoUrl.toString()
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(dataModel = null, e.message)
        }
    }

    suspend fun signOut() {
        try {
            credentialManager.clearCredentialState(ClearCredentialStateRequest())
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    fun getSignedInUser(): UserData? = auth.currentUser?.run {
        UserData(
            id = uid,
            name = displayName,
            email = email,
            profileUrl = photoUrl?.toString()
        )
    }

    private fun createSignInRequest(): GetCredentialRequest {
        val option = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setAutoSelectEnabled(true)
            .setServerClientId(BuildConfig.webClientId)
            .build()

        return GetCredentialRequest.Builder()
            .addCredentialOption(option)
            .build()
    }

}
