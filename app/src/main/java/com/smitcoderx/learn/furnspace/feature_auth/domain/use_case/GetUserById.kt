package com.smitcoderx.learn.furnspace.feature_auth.domain.use_case

import com.smitcoderx.learn.furnspace.feature_auth.domain.repository.UserRepository

class GetUserById(private val userRepository: UserRepository) {

    suspend operator fun invoke(id: String) = userRepository.getUserById(id)

}