package com.smitcoderx.learn.furnspace.feature_auth.presentation

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smitcoderx.learn.furnspace.feature_auth.domain.use_case.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val useCase: UseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state = _state

    private val _dataState = MutableStateFlow(AuthState())
    val dataState = _dataState

    fun loginUser(context: Context) = viewModelScope.launch {
        _state.update {
            it.copy(
                isSignInSuccessful = useCase.login(context)?.data != null,
                response = useCase.login(context)?.data,
                signInError = useCase.login(context)?.message
            )
        }
    }

    fun onSignInData(data: Bundle) = viewModelScope.launch {
        _dataState.update {
            it.copy(
                isSignInSuccessful = useCase.loginResponse.invoke(data).data != null,
                signInError = useCase.loginResponse.invoke(data).message
            )
        }
    }

    fun onSignInResult(id: String) {
        _dataState.update {
            it.copy(
                isSignInSuccessful = id.isNotBlank(),
                signInError = ""
            )
        }
    }
}