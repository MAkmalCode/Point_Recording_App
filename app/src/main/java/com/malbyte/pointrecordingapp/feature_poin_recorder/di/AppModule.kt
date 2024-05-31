package com.malbyte.pointrecordingapp.feature_poin_recorder.di

import android.app.Application
import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.local.HistoryDatabase
import com.malbyte.pointrecordingapp.feature_poin_recorder.data.repository.PoinRecordingRepositoryImpl
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.repository.PoinRecordingRepository
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.employee_use_case.DeleteEmployeeUseCase
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.history_use_case.DeleteHistoryUseCase
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.employee_use_case.GetAllEmployeeListUseCase
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.history_use_case.GetAllHistoryUseCase
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.employee_use_case.InsertEmployeeUseCase
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.history_use_case.InsertHistoryUseCase
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.PoinRecordUseCases
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.account_use_case.DeleteAccountUseCase
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.account_use_case.GetAllAccountUseCase
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.account_use_case.UpdateAccountUseCase
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.account_use_case.UpdatePoinAccountUseCase
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.auth_use_case.SignInUseCase
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.auth_use_case.SignUpUseCase
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.employee_use_case.UpdateEmployeePoinUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.minimalSettings
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient{
        return createSupabaseClient(
            supabaseUrl = "https://svjzaobwvxhgudjqcpoj.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InN2anphb2J3dnhoZ3VkanFjcG9qIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTE0MjI1ODAsImV4cCI6MjAyNjk5ODU4MH0.WuTgRali6MN_JRW2l4D1QMp4kpvCkshIVvZeMJZzTNM"
        ) {
            install(Auth)
            install(Postgrest)
            install(Realtime)
        }
    }

    @Provides
    @Singleton
    fun provideHistoryDatabse(app: Application): HistoryDatabase {
        return HistoryDatabase.getInstance(app)
    }

    @Provides
    @Singleton
    fun providePoinRecordRepository(client: SupabaseClient, historyDatabase: HistoryDatabase): PoinRecordingRepository {
        return PoinRecordingRepositoryImpl(client, historyDatabase.historyDao)
    }

    @Provides
    @Singleton
    fun providePoinRecordUseCases( repository: PoinRecordingRepository): PoinRecordUseCases {
        return PoinRecordUseCases(
            getAllEmployeeListUseCase = GetAllEmployeeListUseCase(repository),
            updateEmployeePoinUseCase = UpdateEmployeePoinUseCase(repository),
            insertEmployeeUseCase = InsertEmployeeUseCase(repository),
            deleteEmployeeUseCase = DeleteEmployeeUseCase(repository),
            getAllHistoryUseCase = GetAllHistoryUseCase(repository),
            insertHistoryUseCase = InsertHistoryUseCase(repository),
            deleteHistoryUseCase = DeleteHistoryUseCase(repository),
            signUpUseCase = SignUpUseCase(repository),
            signInUseCase = SignInUseCase(repository),
            updateAccountUseCase = UpdateAccountUseCase(repository),
            getAllAccountUseCase = GetAllAccountUseCase(repository),
            deleteAccountUseCase = DeleteAccountUseCase(repository),
            updatePoinAccountUseCase = UpdatePoinAccountUseCase(repository)
        )
    }
}