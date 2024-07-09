package com.smitcoderx.learn.furnspace.feature_auth.domain.repository

import android.content.Context
import android.os.Bundle
import androidx.credentials.GetCredentialResponse
import com.smitcoderx.learn.furnspace.core.Response
import com.smitcoderx.learn.furnspace.feature_auth.domain.data.UserData

interface UserRepository {

    suspend fun getUserById(id: String): Response<UserData>
    suspend fun insertUser(userData: UserData): Response<Boolean>
    suspend fun updateUser(id: String, userData: UserData): Response<Boolean>
    suspend fun deleteUser(id: String): Response<Boolean>
    suspend fun login(context: Context): Response<GetCredentialResponse>?
    suspend fun loginResponse(credential: Bundle): Response<UserData>
    fun getCurrentUser(): Response<UserData>
    suspend fun logout(): Response<Boolean>
}