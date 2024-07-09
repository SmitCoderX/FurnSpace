package com.smitcoderx.learn.furnspace.feature_auth.domain.use_case

import com.smitcoderx.learn.furnspace.feature_auth.domain.repository.UserRepository

class CurrentUser(private val repository: UserRepository) {
    operator fun invoke() = repository.getCurrentUser()

}