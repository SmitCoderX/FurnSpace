package com.smitcoderx.learn.furnspace.feature_auth.domain.use_case

import com.smitcoderx.learn.furnspace.feature_auth.domain.repository.UserRepository

class Logout(private val user: UserRepository) {

    suspend operator fun invoke() = user.logout()

}