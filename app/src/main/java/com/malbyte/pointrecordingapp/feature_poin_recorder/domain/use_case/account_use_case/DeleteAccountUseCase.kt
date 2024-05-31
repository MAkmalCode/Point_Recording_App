package com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.account_use_case

import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.remote.model.Account
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.repository.PoinRecordingRepository
import com.rmaprojects.apirequeststate.RequestState
import kotlinx.coroutines.flow.Flow

class DeleteAccountUseCase(
    private val repository: PoinRecordingRepository
) {

    operator fun invoke(userId: String): Flow<RequestState<Boolean>>{
        return repository.deleteAccount(userId)
    }
}