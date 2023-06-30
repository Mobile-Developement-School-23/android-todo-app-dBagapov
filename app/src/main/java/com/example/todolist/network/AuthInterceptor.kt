package com.example.todolist.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val token: String = "Bagapov_D") : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}