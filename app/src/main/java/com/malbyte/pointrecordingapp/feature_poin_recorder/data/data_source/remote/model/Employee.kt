package com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.remote.model

import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Employee (
    @SerialName("id") val id : String,
    @SerialName("name") val name: String?,
    @SerialName("position") val position: String?,
    @SerialName("poin") val poin: Int?,
    @SerialName("created_at") val createdAt: String?
)