package com.smitcoderx.learn.furnspace.feature_auth.domain.use_case

import com.smitcoderx.learn.furnspace.feature_auth.domain.repository.UserRepository

class DeleteUser(private val userRepository: UserRepository) {

    suspend operator fun invoke(userId: String) {
        userRepository.deleteUser(userId)
    }

}