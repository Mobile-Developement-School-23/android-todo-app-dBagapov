package com.example.todolist.network

import com.google.gson.annotations.SerializedName

data class ToDoItemResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("done")
    var isCompleted: Boolean,
    @SerializedName("create_at")
    val dateCreated: Long,
    @SerializedName("changed_at")
    val dateChange: String,
    @SerializedName("deadline")
    val deadline: Long?,
    @SerializedName("importance")
    val importance: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("last_update_by")
    val lastUpdateBy: String
)