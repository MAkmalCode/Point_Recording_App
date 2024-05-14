package com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.local.model.PoinHistory

@Database(version = 2, entities = [PoinHistory::class])
abstract class HistoryDatabase : RoomDatabase() {

    abstract val historyDao: HistoryDao

    companion object {
        @Volatile
        private var INSTANCE: HistoryDatabase? = null

        fun getInstance(context: Context): HistoryDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context = context,
                    klass = HistoryDatabase::class.java,
                    name = "history.db"
                ).fallbackToDestructiveMigration().build()
            }
        }
    }
}