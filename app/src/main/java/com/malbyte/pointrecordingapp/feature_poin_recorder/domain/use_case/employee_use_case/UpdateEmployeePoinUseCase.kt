package com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.employee_use_case

import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.remote.model.Employee
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.repository.PoinRecordingRepository
import com.rmaprojects.apirequeststate.RequestState
import kotlinx.coroutines.flow.Flow

class UpdateEmployeePoinUseCase(
    private val repository: PoinRecordingRepository
) {
    operator fun invoke(id: String, poin: Int): Flow<RequestState<Employee>> {
        return repository.updatePoin(id, poin)
    }
}