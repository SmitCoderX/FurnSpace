package com.smitcoderx.learn.furnspace.feature_auth.domain.use_case

import android.os.Bundle
import com.smitcoderx.learn.furnspace.feature_auth.domain.repository.UserRepository

class LoginResponse(private val userRepository: UserRepository) {

    suspend operator fun invoke(credential: Bundle) = userRepository.loginResponse(credential)


}