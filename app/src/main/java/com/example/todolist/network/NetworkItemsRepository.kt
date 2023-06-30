/*
package com.example.todolist.network

class NetworkItemsRepository(private val apiService: ApiService) {
        suspend fun getTasks(): List<Task> {
            val response = apiService.getTasks()
            if (response.isSuccessful) {
                return response.body() ?: emptyList()
            } else {
                throw ApiException(response.code(), response.message())
            }
        }

        suspend fun createTask(task: Task): Task {
            val response = apiService.createTask(task)
            if (response.isSuccessful) {
                return response.body() ?: throw ApiException(response.code(), "Empty response")
            } else {
                throw ApiException(response.code(), response.message())
            }
        }

        suspend fun updateTask(taskId: String, task: Task): Task {
            val response = apiService.updateTask(taskId, task)
            if (response.isSuccessful) {
                return response.body() ?: throw ApiException(response.code(), "Empty response")
            } else {
                throw ApiException(response.code(), response.message())
            }
        }

        suspend fun deleteTask(taskId: String) {
            val response = apiService.deleteTask(taskId)
            if (!response.isSuccessful) {
                throw ApiException(response.code(), response.message())
            }
        }
    }

}
*/
