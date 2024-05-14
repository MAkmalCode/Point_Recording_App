package com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Account (
    @SerialName("id") val id : String,
    @SerialName("username") val username: String,
    @SerialName("position") val position: String,
    @SerialName("poin") val poin: Int,
    @SerialName("created_at") val createdAt: String,
)