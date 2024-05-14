package com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.employee_use_case

import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.remote.model.Employee
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.repository.PoinRecordingRepository
import com.rmaprojects.apirequeststate.RequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllEmployeeListUseCase(
    private val repository: PoinRecordingRepository
) {
    suspend fun fetchData(): Result<Flow<List<Employee>>> {
        return repository.getAllEmployee()
    }

    suspend fun unsubscribeChannel() {
        repository.unsubscribeChannel()
    }
}