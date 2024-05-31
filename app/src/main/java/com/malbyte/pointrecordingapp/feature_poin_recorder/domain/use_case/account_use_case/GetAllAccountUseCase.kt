package com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.account_use_case

import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.remote.model.Account
import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.remote.model.Employee
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.repository.PoinRecordingRepository
import kotlinx.coroutines.flow.Flow

class GetAllAccountUseCase(
    private val repository: PoinRecordingRepository
) {

    suspend fun fetchData(): Result<Flow<List<Account>>> {
        return repository.getAccount()
    }

    suspend fun unsubscribeChannel() {
        repository.unsubscribeChannel()
    }
}