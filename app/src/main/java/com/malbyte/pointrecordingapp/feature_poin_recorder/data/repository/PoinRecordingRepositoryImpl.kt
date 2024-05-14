package com.malbyte.pointrecordingapp.feature_poin_recorder.data.repository

import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.local.HistoryDao
import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.local.LocalUser
import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.local.model.PoinHistory
import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.remote.model.Account
import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.remote.model.Employee
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.repository.PoinRecordingRepository
import com.rmaprojects.apirequeststate.RequestState
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.postgresListDataFlow
import io.github.jan.supabase.realtime.realtime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class PoinRecordingRepositoryImpl(
    private val client: SupabaseClient,
    private val historyDao: HistoryDao
) : PoinRecordingRepository {

    private val employeeChannel = client.channel("employee")

    override suspend fun getAllEmployee(): Result<Flow<List<Employee>>> {

        val data = employeeChannel.postgresListDataFlow(
            schema = "public",
            table = "employee",
            primaryKey = Employee::id
        ).flowOn(Dispatchers.IO)


        employeeChannel.subscribe()

        return Result.success(data)
    }

    override suspend fun unsubscribeChannel() {
        employeeChannel.unsubscribe()
        client.realtime.removeChannel(employeeChannel)
    }

    override fun updatePoin(id: String, poin: Int): Flow<RequestState<Employee>> {
        return flow {
            try {
                emit(
                    RequestState.Success(
                        client.from("employee")
                            .update(
                                update = {
                                    set("poin", poin)
                                },
                                request = {
                                    filter {
                                        eq("id", id)
                                    }
                                }
                            ).decodeSingle<Employee>()
                    )
                )
            } catch (e: Exception) {
                emit(
                    RequestState.Error(
                        e.message.toString()
                    )
                )
            }
        }
    }

    override fun insertEmployee(employee: Employee): Flow<RequestState<Employee>> {
        return flow {
            try {

                RequestState.Success(
                    client.from("employee").insert(employee)
                )
            } catch (e: Exception) {

                RequestState.Error(e.message.toString())
            }
        }
    }

    override fun deleteEmployee(id: String): Flow<RequestState<Employee>> {
        return flow {
            try {

                RequestState.Success(
                    client.from("employee").delete(
                        request = {
                            filter {
                                eq("id", id)
                            }
                        }
                    )
                )
            } catch (e: Exception) {

                RequestState.Error(e.message.toString())
            }
        }
    }

    override fun signUp(
        email: String,
        password: String,
        username: String,
        position: String
    ): Flow<RequestState<Boolean>> = flow {
        try {
            client.auth.signUpWith(Email) {
                this.email = email
                this.password = password
                data = buildJsonObject {
                    put("username", username)
                    put("position", position)
                }
            }
            emit(RequestState.Success(true))
        } catch (e: Exception) {
            emit(RequestState.Error(e.toString()))
        }
    }

    override fun signIn(email: String, password: String): Flow<RequestState<Boolean>> = flow {

        try {

            client.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            val user = client.auth.currentSessionOrNull()?.user
            val publicUser = client.from("account")
                .select {
                    filter {
                        Account::id eq user?.id
                    }
                }.decodeSingle<Account>()
            LocalUser.apply {
                this.username = publicUser.username
                this.position = publicUser.position
                this.poin = publicUser.poin
                this.id = publicUser.id
            }
            emit(RequestState.Success(true))
        } catch (e: Exception) {
            emit(RequestState.Error(e.message.toString()))
        }
    }

    override fun updateAccount(id: String, username: String): Flow<RequestState<Boolean>> = flow {
        try {

            client.auth.updateUser {
                data = buildJsonObject {
                    put("username", username)
                }
            }
            emit(RequestState.Success(true))
        } catch (e: Exception) {
            emit(RequestState.Error(e.message.toString()))
        }
    }

    override fun getAllHistory(): Flow<List<PoinHistory>> {
        return historyDao.getAllHistory()
    }

    override suspend fun insertHistory(poinHistory: PoinHistory) {
        return historyDao.insertHistory(poinHistory)
    }

    override suspend fun deleteHistory(poinHistory: PoinHistory) {
        return historyDao.deleteHistory(poinHistory)
    }
}
