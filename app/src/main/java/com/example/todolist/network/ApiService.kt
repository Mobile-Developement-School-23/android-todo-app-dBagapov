/*
package com.example.todolist.network

import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("tasks")
    suspend fun getTasks(): Response<List<Task>>

    @POST("tasks")
    suspend fun createTask(@Body task: Task): Response<Task>

    @PUT("tasks/{taskId}")
    suspend fun updateTask(@Path("taskId") taskId: String, @Body task: Task): Response<Task>

    @DELETE("tasks/{taskId}")
    suspend fun deleteTask(@Path("taskId") taskId: String): Response<Unit>
}*/
