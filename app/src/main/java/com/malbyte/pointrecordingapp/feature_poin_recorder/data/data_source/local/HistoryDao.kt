package com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.local.model.PoinHistory
import com.rmaprojects.apirequeststate.RequestState
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Query("SELECT * FROM poin_history ")
    fun getAllHistory(): Flow<List<PoinHistory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(poinHistory: PoinHistory)

    @Delete
    suspend fun deleteHistory(poinHistory: PoinHistory)
}