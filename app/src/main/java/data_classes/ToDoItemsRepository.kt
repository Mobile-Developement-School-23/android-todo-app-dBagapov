package data_classes

import android.content.Context
import data_classes.ToDoItem

class ToDoItemsRepository {
    private var notices = mutableListOf<ToDoItem>()

    init {
        val first = ToDoItem("1",false,"Сделать домашнее задание",  true, "10.10.2023", "10.10.2023")
        val second = ToDoItem("2",false,"Убраться в комнате", false, "12.10.2023", null)
        val third = ToDoItem("3",false,"Cделать лабы по проге",true,"12.10.2023", "10.10.2023")
        val fourth = ToDoItem("4",true,"Купить шоколадку",  true,"12.10.2023", "10.10.2023")
        notices.add(first)
        notices.add(second)
        notices.add(third)
        notices.add(fourth)
    }

    fun addNotice(note: ToDoItem) {
        notices.add(note)
    }

    fun deleteNotice(note: ToDoItem) {
        notices.remove(note)
    }

    fun getSize() = notices.size

    operator fun get(position: Int): ToDoItem {
        return notices[position]
    }

    fun getNotices(context: Context): List<ToDoItem> {
        return notices
    }
}