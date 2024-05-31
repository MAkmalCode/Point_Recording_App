package com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.remote

import com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.remote.response.create_user_responses.CreateUserResponse
import kotlinx.serialization.json.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface ApiService {
    @POST("signup")
    suspend fun createUser(
        @Body jsonObject: JsonObject,
        @Header("apiKey") apikey: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InN2anphb2J3dnhoZ3VkanFjcG9qIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTE0MjI1ODAsImV4cCI6MjAyNjk5ODU4MH0.WuTgRali6MN_JRW2l4D1QMp4kpvCkshIVvZeMJZzTNM",
        @Header("Authorization") authorization: String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InN2anphb2J3dnhoZ3VkanFjcG9qIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTE0MjI1ODAsImV4cCI6MjAyNjk5ODU4MH0.WuTgRali6MN_JRW2l4D1QMp4kpvCkshIVvZeMJZzTNM"
    ): Response<CreateUserResponse>
}

