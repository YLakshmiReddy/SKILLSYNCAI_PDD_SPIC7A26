package com.simats.SkillSyncAI

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

import retrofit2.http.Body
import retrofit2.http.Body as BodyAnnotation

interface ApiService {
    @Multipart
    @POST("analyze")
    suspend fun uploadResume(
        @Part file: MultipartBody.Part
    ): Response<AnalysisResponse>

    @POST("register")
    suspend fun register(
        @Body req: RegisterRequest
    ): Response<RegisterResponse>

    @POST("login")
    suspend fun login(
        @Body req: LoginRequest
    ): Response<LoginResponse>

    @POST("google-login")
    suspend fun googleLogin(
        @Body req: GoogleLoginRequest
    ): Response<LoginResponse>
}

// This matches the JSON output from your FastAPI backend
data class AnalysisResponse(
    val filename: String,
    val suggested_path: String,
    val match_score: Int,
    val match_details: Map<String, Int>,
    val pros: String,
    val cons: String,
    val db_status: String
)

data class RegisterRequest(
    val full_name: String,
    val email: String,
    val password: String
)

data class UserDetail(
    val id: String,
    val full_name: String,
    val email: String
)

data class RegisterResponse(
    val message: String,
    val user: UserDetail
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val message: String,
    val token: String,
    val user: UserDetail
)

data class GoogleLoginRequest(
    val id_token: String
)