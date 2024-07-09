package com.smitcoderx.learn.furnspace.feature_auth.data.repository

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.CollectionReference
import com.smitcoderx.learn.furnspace.BuildConfig
import com.smitcoderx.learn.furnspace.core.Response
import com.smitcoderx.learn.furnspace.feature_auth.domain.data.UserData
import com.smitcoderx.learn.furnspace.feature_auth.domain.repository.UserRepository
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    @ActivityContext private val context: Context,
    private val userRef: CollectionReference,
    private val auth: FirebaseAuth
) : UserRepository {

    private val credentialManager = CredentialManager.create(context)

    override suspend fun getUserById(id: String): Response<UserData> {
        return Response.Success(UserData(id = id, name = "", email = "", profileUrl = ""))
    }

    override suspend fun insertUser(userData: UserData): Response<Boolean> {
        return Response.Success(false)
    }

    override suspend fun updateUser(id: String, userData: UserData): Response<Boolean> {
        return Response.Success(false)
    }

    override suspend fun deleteUser(id: String): Response<Boolean> {
        return Response.Success(false)
    }

    override suspend fun login(context: Context): Response<GetCredentialResponse> {
        try {
            return Response.Success(
                credentialManager.getCredential(
                    request = createSignInRequest(),
                    context = context,
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            return Response.Error(e.message.toString())
        }
    }

    override suspend fun logout(): Response<Boolean> {
        try {
            credentialManager.clearCredentialState(ClearCredentialStateRequest())
            auth.signOut()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            Response.Success(false)
        }
        return Response.Loading()
    }

    override suspend fun loginResponse(credential: Bundle): Response<UserData> {
        val googleIdToken = GoogleIdTokenCredential.createFrom(credential)
        val googleCredential = GoogleAuthProvider.getCredential(googleIdToken.idToken, null)
        try {
            val user = auth.signInWithCredential(googleCredential).await().user
            Log.d("TAG", "signInWithCredential: ${user?.displayName}")
            user?.run {
                return Response.Success(
                    UserData(
                        id = uid,
                        name = displayName,
                        email = email,
                        profileUrl = photoUrl.toString(),
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            return Response.Error(e.message.toString())
        }
        return Response.Loading()
    }


    private fun createSignInRequest(): GetCredentialRequest {
        val option = GetGoogleIdOption.Builder().setFilterByAuthorizedAccounts(false)
            .setAutoSelectEnabled(true).setServerClientId(BuildConfig.webClientId).build()

        return GetCredentialRequest.Builder().addCredentialOption(option).build()
    }


    override fun getCurrentUser(): Response<UserData> {
        if (auth.currentUser != null) {
            auth.currentUser?.run {
                return Response.Success(
                    UserData(
                        id = uid,
                        name = displayName,
                        email = email,
                        profileUrl = photoUrl.toString()
                    )
                )
            }
        } else {
            return Response.Error("No Current User")
        }
        return Response.Loading()
    }
}