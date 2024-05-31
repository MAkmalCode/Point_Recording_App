package com.malbyte.pointrecordingapp.ui.screen.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.local.model.PoinHistory
import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.remote.model.Account
import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.remote.model.Employee
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.PoinRecordUseCases
import com.rmaprojects.apirequeststate.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: PoinRecordUseCases
) : ViewModel() {

    private val _listEmployeeState =
        MutableStateFlow<RequestState<List<Employee>>>(RequestState.Loading)
    val listEmployeeState = _listEmployeeState.asStateFlow().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(500),
        RequestState.Idle
    )

    private val _listAccountState =
        MutableStateFlow<RequestState<List<Account>>>(RequestState.Loading)
    val listAccountState = _listAccountState.asStateFlow().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(500),
        RequestState.Idle
    )

    fun connectToRealTime() {
        viewModelScope.launch(Dispatchers.IO) {

            useCases.getAllAccountUseCase.fetchData()
                .onSuccess { flow ->
                    flow.onEach {
                        _listAccountState.emit(RequestState.Success(it))
                    }.collect()
                }
                .onFailure {
                    _listAccountState.emit(RequestState.Error(it.message.toString()))
                }
        }
    }

    fun leaveRealtimeChannel() = viewModelScope.launch {
        useCases.getAllEmployeeListUseCase.unsubscribeChannel()
    }

    private val _deleteAccountState = MutableStateFlow<RequestState<Boolean>>(RequestState.Loading)
    val deleteAccountState = _deleteAccountState.asStateFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            RequestState.Idle
        )

    fun deleteAccount(userId: String) = viewModelScope.launch {
        _deleteAccountState.emitAll(useCases.deleteAccountUseCase(userId))
    }

    private val _updateState = MutableStateFlow<RequestState<Boolean>>(RequestState.Idle)
    val updateState = _updateState.asStateFlow().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(500),
        RequestState.Idle
    )

    fun updatePoinAccount(id: String, poin: Int){
        viewModelScope.launch {
            _updateState.emitAll(useCases.updatePoinAccountUseCase(id, poin))
        }
    }

    private val _updataState = MutableStateFlow<RequestState<Employee>>(RequestState.Idle)
    val updataState = _updataState.asStateFlow().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(500),
        RequestState.Idle
    )

    fun updateEmployeePoin(id: String, poin: Int) {
        viewModelScope.launch {
            _updataState.emitAll(useCases.updateEmployeePoinUseCase(id, poin))
        }
    }

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    fun onSearchTextChange (text: String){
        _searchText.value = text
    }

    fun onToogleSearch() {
        _isSearching.value = !_isSearching.value
        if (!_isSearching.value) {
            onSearchTextChange("")
        }
    }

    private val _insertState = MutableStateFlow<RequestState<Employee>>(RequestState.Idle)
    val insertState = _updataState.asStateFlow().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(500),
        RequestState.Idle
    )

    fun insertEmployee(employee: Employee) {
        viewModelScope.launch {
            _insertState.emitAll(useCases.insertEmployeeUseCase(employee))
        }
    }

    private val _deltetState = MutableStateFlow<RequestState<Employee>>(RequestState.Idle)
    val deleteState = _updataState.asStateFlow().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(500),
        RequestState.Idle
    )

    fun deleteEmployee(id: String) {
        viewModelScope.launch {
            _deltetState.emitAll(useCases.deleteEmployeeUseCase(id))
        }
    }

    fun insertHistory(poinHistory: PoinHistory) {
        viewModelScope.launch {
            useCases.insertHistoryUseCase(poinHistory)
        }
    }
}