package com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.history_use_case

import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.local.model.PoinHistory
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.repository.PoinRecordingRepository

class DeleteHistoryUseCase(
    private val repository: PoinRecordingRepository
) {

    suspend operator fun invoke(poinHistory: PoinHistory) {
        return repository.deleteHistory(poinHistory)
    }
}