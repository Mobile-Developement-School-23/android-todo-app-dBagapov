package com.example.todolist.data.repository

import android.content.Context
import com.example.todolist.data.models.Importance
import com.example.todolist.data.models.ToDoItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date
import java.util.UUID

/*object ToDoItemsRepository {
    private var notices = mutableListOf<ToDoItem>()

    init {
        val first = ToDoItem("1",false,"Сделать домашнее задание",  true, Date(), "10.10.2023", Importance.COMMON)
        val second = ToDoItem("2",false,"Убраться в комнате", false, Date(), null, Importance.COMMON)
        val third = ToDoItem("3",false,"Cделать лабы по проге",true, Date(), "10.10.2023", Importance.COMMON)
        val fourth = ToDoItem("4",true,"Купить шоколадку",  true, Date(), "10.10.2023", Importance.COMMON)
        notices.add(first)
        notices.add(second)
        notices.add(third)
        notices.add(fourth)
    }

    fun addNotice(note: ToDoItem) {
        if (notices.find {it.id == note.id} == null) {
            notices.add(note)
        } else {
            notices[notices.indexOfFirst {it.id == note.id}] = note
        }
    }

    fun getSize() = notices.size

    operator fun get(position: Int): ToDoItem {
        return notices[position]
    }

    fun getNewId(): String {
        return if (notices.size > 0) {
            (notices[notices.size - 1].id.toInt() + 1).toString()
        } else {
            "1"
        }
    }

    fun getById(id: String): ToDoItem? {
        return notices.find {it.id == id}
    }

    fun deleteById(id: String) {
        val noteToRemove = notices.find { it.id == id }

        if (noteToRemove != null) {
            notices.remove(noteToRemove)
        }
    }

    fun getNotices(): List<ToDoItem> {
        return notices
    }
}*/

object ToDoItemsRepository {
    private var notices = mutableListOf<ToDoItem>()
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    init {
        val first = ToDoItem("1",false,"Сделать домашнее задание",  true, Date(), "10.10.2023", Importance.COMMON)
        val second = ToDoItem("2",false,"Убраться в комнате", false, Date(), null, Importance.COMMON)
        val third = ToDoItem("3",false,"Cделать лабы по проге",true, Date(), "10.10.2023", Importance.COMMON)
        val fourth = ToDoItem("4",true,"Купить шоколадку",  true, Date(), "10.10.2023", Importance.COMMON)
        notices.add(first)
        notices.add(second)
        notices.add(third)
        notices.add(fourth)
    }

    suspend fun addNotice(note: ToDoItem) {
        withContext(ioDispatcher) {
            if (notices.find { it.id == note.id } == null) {
                notices.add(note)
            } else {
                notices[notices.indexOfFirst { it.id == note.id }] = note
            }
        }
    }

    suspend fun getSize(): Int {
        return withContext(ioDispatcher) {
            notices.size
        }
    }

    suspend fun get(position: Int): ToDoItem {
        return withContext(ioDispatcher) {
            notices[position]
        }
    }

    suspend fun getNewId(): String {
        return withContext(ioDispatcher) {
            UUID.randomUUID().toString()
        }
    }

    suspend fun getById(id: String): ToDoItem? {
        return withContext(ioDispatcher) {
            notices.find { it.id == id }
        }
    }

    suspend fun deleteById(id: String) {
        withContext(ioDispatcher) {
            val noteToRemove = notices.find { it.id == id }
            if (noteToRemove != null) {
                notices.remove(noteToRemove)
            }
        }
    }

    suspend fun getNotices(): List<ToDoItem> {
        return withContext(ioDispatcher) {
            notices.toList()
        }
    }
}