package com.smitcoderx.learn.furnspace.di

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.firestore
import com.smitcoderx.learn.furnspace.core.Constants.USERS
import com.smitcoderx.learn.furnspace.feature_auth.data.repository.UserRepositoryImpl
import com.smitcoderx.learn.furnspace.feature_auth.domain.repository.UserRepository
import com.smitcoderx.learn.furnspace.feature_auth.domain.use_case.CurrentUser
import com.smitcoderx.learn.furnspace.feature_auth.domain.use_case.DeleteUser
import com.smitcoderx.learn.furnspace.feature_auth.domain.use_case.GetUserById
import com.smitcoderx.learn.furnspace.feature_auth.domain.use_case.InsertUser
import com.smitcoderx.learn.furnspace.feature_auth.domain.use_case.Login
import com.smitcoderx.learn.furnspace.feature_auth.domain.use_case.LoginResponse
import com.smitcoderx.learn.furnspace.feature_auth.domain.use_case.Logout
import com.smitcoderx.learn.furnspace.feature_auth.domain.use_case.UpdateUser
import com.smitcoderx.learn.furnspace.feature_auth.domain.use_case.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideUserReference() = Firebase.firestore.collection(USERS)

    @Provides
    fun provideAuth() = Firebase.auth

    @Provides
    fun provideUserRepository(
        @ApplicationContext context: Context,
        userRef: CollectionReference,
        auth: FirebaseAuth
    ): UserRepository =
        UserRepositoryImpl(context, userRef = userRef, auth = auth)

    @Provides
    fun providesUserUseCase(
        repository: UserRepository
    ) = UseCase(
        login = Login(repository),
        insertUser = InsertUser(repository),
        updateUser = UpdateUser(repository),
        deleteUser = DeleteUser(repository),
        getUserById = GetUserById(repository),
        loginResponse = LoginResponse(repository),
        currentUser = CurrentUser(repository),
        logout = Logout(repository)
    )
}