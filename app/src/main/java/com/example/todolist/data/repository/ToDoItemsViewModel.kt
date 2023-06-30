/*
package com.example.todolist.data.repository

import androidx.lifecycle.ViewModel
import com.example.todolist.data.models.ToDoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Что-то не работало, поэтому пока не смог применить ее
class ToDoItemsViewModel : ViewModel() {
    private val repository = ToDoItemsRepository

    fun addNotice(note: ToDoItem) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addNotice(note)
        }
    }

    suspend fun getSize(): Int {
        return repository.getSize()
    }

    suspend fun get(position: Int): ToDoItem {
        return repository.get(position)
    }

    suspend fun getNewId(): String {
        return repository.getNewId()
    }

    suspend fun getById(id: String): ToDoItem? {
        return repository.getById(id)
    }

    fun deleteById(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteById(id)
        }
    }

    suspend fun getNotices(): List<ToDoItem> {
        return repository.getNotices()
    }
}*/
