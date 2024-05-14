package com.malbyte.pointrecordingapp.ui.screen.auth.login_screen

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
class LoginViewModel @Inject constructor(
    private val useCases: PoinRecordUseCases
): ViewModel() {

    private val _loginState = MutableStateFlow<RequestState<Boolean>>(RequestState.Idle)
    val loginState = _loginState.asStateFlow().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        RequestState.Idle
    )

    fun signIn(email: String, password: String){
        viewModelScope.launch {
            _loginState.emitAll(useCases.signInUseCase(email, password))
        }
    }
}