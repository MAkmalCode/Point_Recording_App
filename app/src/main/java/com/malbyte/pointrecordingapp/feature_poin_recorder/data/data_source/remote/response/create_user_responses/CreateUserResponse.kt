package com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.remote.response.create_user_responses


import com.google.gson.annotations.SerializedName

data class CreateUserResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expires_at")
    val expiresAt: Int,
    @SerializedName("expires_in")
    val expiresIn: Int,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("user")
    val user: User
)