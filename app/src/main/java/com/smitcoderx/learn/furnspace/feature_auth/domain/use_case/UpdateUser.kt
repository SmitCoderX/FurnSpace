package com.smitcoderx.learn.furnspace.feature_auth.domain.use_case

import com.smitcoderx.learn.furnspace.feature_auth.domain.data.UserData
import com.smitcoderx.learn.furnspace.feature_auth.domain.repository.UserRepository

class UpdateUser(private val repository: UserRepository) {

    suspend operator fun invoke(id: String, user: UserData) {
        repository.updateUser(id, user)
    }


}