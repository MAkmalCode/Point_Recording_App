package com.malbyte.pointrecordingapp.feature_poin_recorder.domain.repository

import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.local.model.PoinHistory
import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.remote.model.Employee
import com.rmaprojects.apirequeststate.RequestState
import kotlinx.coroutines.flow.Flow

interface PoinRecordingRepository {

    // Supabase
    suspend fun getAllEmployee(): Result<Flow<List<Employee>>>
    suspend fun unsubscribeChannel()
    fun updatePoin(id: String, poin: Int): Flow<RequestState<Employee>>
    fun insertEmployee(employee: Employee): Flow<RequestState<Employee>>
    fun deleteEmployee(id: String): Flow<RequestState<Employee>>
    fun signUp(
        email: String,
        password: String,
        username: String,
        position: String
    ): Flow<RequestState<Boolean>>
    fun signIn(
        email: String,
        password: String
    ): Flow<RequestState<Boolean>>
    fun updateAccount(id: String, username: String): Flow<RequestState<Boolean>>

    // Room
    fun getAllHistory(): Flow<List<PoinHistory>>
    suspend fun insertHistory(poinHistory: PoinHistory)
    suspend fun deleteHistory(poinHistory: PoinHistory)
}

















