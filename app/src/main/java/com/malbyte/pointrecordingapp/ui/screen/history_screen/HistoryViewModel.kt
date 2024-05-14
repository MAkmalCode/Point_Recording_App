package com.malbyte.pointrecordingapp.ui.screen.history_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.local.model.PoinHistory
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.PoinRecordUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val useCases: PoinRecordUseCases
): ViewModel() {

    val getHistory = useCases.getAllHistoryUseCase().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(500),
        emptyList()
    )

    fun deleteHistory(poinHistory: PoinHistory){
        viewModelScope.launch {
            useCases.deleteHistoryUseCase(poinHistory)
        }
    }
}