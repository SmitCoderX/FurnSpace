package com.smitcoderx.learn.furnspace.feature_auth.domain.use_case

import android.content.Context
import com.smitcoderx.learn.furnspace.feature_auth.domain.repository.UserRepository

class Login(private val repository: UserRepository) {

    suspend operator fun invoke(context: Context) = repository.login(context)

}