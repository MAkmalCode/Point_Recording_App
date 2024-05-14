package com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "poin_history")
data class PoinHistory (
    @PrimaryKey(true) val id : Int?,
    val employeeName: String,
    val fault: String,
    val poinIn: Int,
    val poinOut: Int,
    val recorderName: String,
    val createdAt: String
)