package com.example.todolist.data.models

import java.util.Date

data class ToDoItem(
    val id: String,
    var isCompleted: Boolean,
    val text: String,
    val deadlineFlag: Boolean?,
    val dateCreated: Date,
    val deadline: String?,
    val importance: Importance
)