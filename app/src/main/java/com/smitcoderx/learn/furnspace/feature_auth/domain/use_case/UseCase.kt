package com.smitcoderx.learn.furnspace.feature_auth.domain.use_case

data class UseCase(
    val login: Login,
    val insertUser: InsertUser,
    val updateUser: UpdateUser,
    val deleteUser: DeleteUser,
    val getUserById: GetUserById,
    val loginResponse: LoginResponse,
    val currentUser: CurrentUser,
    val logout: Logout
)
