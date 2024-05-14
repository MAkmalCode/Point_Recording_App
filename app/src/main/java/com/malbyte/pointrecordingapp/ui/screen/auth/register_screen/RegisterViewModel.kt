package com.malbyte.pointrecordingapp.ui.screen.auth.register_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.PoinRecordUseCases
import com.rmaprojects.apirequeststate.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val useCase: PoinRecordUseCases
) : ViewModel() {

    private val _registerState = MutableStateFlow<RequestState<Boolean>>(RequestState.Idle)
    val registerState = _registerState
        .asStateFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            RequestState.Idle
        )

    fun signUp(
        email: String,
        password: String,
        username: String,
        position: String
    ) {
        viewModelScope.launch {
            _registerState.emitAll(
                useCase.signUpUseCase(
                    email,
                    password,
                    username,
                    position
                )
            )
        }
    }
}