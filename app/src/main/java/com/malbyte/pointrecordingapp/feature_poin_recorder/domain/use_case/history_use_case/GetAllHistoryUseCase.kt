package com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.history_use_case

import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.local.model.PoinHistory
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.repository.PoinRecordingRepository
import kotlinx.coroutines.flow.Flow

class GetAllHistoryUseCase(
    private val repository: PoinRecordingRepository
) {

    operator fun invoke(): Flow<List<PoinHistory>> {
        return repository.getAllHistory()
    }
}