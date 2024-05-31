package com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.remote.response.create_user_responses


import com.google.gson.annotations.SerializedName

data class AppMetadata(
    @SerializedName("provider")
    val provider: String,
    @SerializedName("providers")
    val providers: List<String>
)