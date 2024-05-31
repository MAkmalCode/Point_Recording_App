package com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.remote.response.create_user_responses


import com.google.gson.annotations.SerializedName

data class UserMetadata(
    @SerializedName("email")
    val email: String,
    @SerializedName("email_verified")
    val emailVerified: Boolean,
    @SerializedName("phone_verified")
    val phoneVerified: Boolean,
    @SerializedName("poin")
    val poin: Int,
    @SerializedName("position")
    val position: String,
    @SerializedName("sub")
    val sub: String,
    @SerializedName("username")
    val username: String
)