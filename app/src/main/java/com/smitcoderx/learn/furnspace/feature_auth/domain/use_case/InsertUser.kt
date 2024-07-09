package com.smitcoderx.learn.furnspace.feature_auth.domain.use_case

import com.smitcoderx.learn.furnspace.feature_auth.domain.data.UserData
import com.smitcoderx.learn.furnspace.feature_auth.domain.repository.UserRepository

class InsertUser(private val repository: UserRepository) {

    suspend operator fun invoke(user: UserData) {
        repository.insertUser(user)
    }

}