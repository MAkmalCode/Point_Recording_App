package com.malbyte.pointrecordingapp.ui.screen.account_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.remote.model.Account
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
class AccountViewModel @Inject constructor(
    private val useCases: PoinRecordUseCases
): ViewModel(){

    private val _updateState = MutableStateFlow<RequestState<Boolean>>(RequestState.Loading)
    val updateState = _updateState.asStateFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            RequestState.Idle
        )

    fun updateAccount(id: String, username: String){
        viewModelScope.launch {
            _updateState.emitAll(useCases.updateAccountUseCase(id, username))
        }
    }
}