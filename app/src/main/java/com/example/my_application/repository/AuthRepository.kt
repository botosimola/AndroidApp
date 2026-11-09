package com.example.my_application.repository

import com.example.my_application.network.RetrofitClient
import com.example.my_application.model.AuthRequest
import android.content.Context

class AuthRepository(context: Context) {
    private val api = RetrofitClient.getInstance(context)

    suspend fun login(email: String, password: String) =
        api.login(AuthRequest(email = email, password = password))
}
