package com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.auth_use_case

import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.repository.PoinRecordingRepository
import com.rmaprojects.apirequeststate.RequestState
import kotlinx.coroutines.flow.Flow

class SignUpUseCase(
    private val repository: PoinRecordingRepository
) {

    operator fun invoke(
        email: String,
        password: String,
        username: String,
        position: String
    ): Flow<RequestState<Boolean>> {
        return repository.signUp(
            email,
            password,
            username,
            position
        )
    }
}