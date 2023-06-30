package com.example.todolist.network

class ApiException(val statusCode: Int, val errorMessage: String) : Exception()