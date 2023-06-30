package com.example.todolist.presentation.adapters

import android.view.View
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.models.ToDoItem


class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    private val textNote: TextView = itemView.findViewById(R.id.textNote)
    private val deadline: TextView = itemView.findViewById(R.id.deadline)
    val changeButton: ImageButton = itemView.findViewById(R.id.changeNote)
    val checkBox: CheckBox = itemView.findViewById(R.id.textNote)

    fun onBind(notice : ToDoItem) {
        textNote.text = notice.text
        checkBox.isChecked = notice.isCompleted

        if (notice.deadlineFlag == true) {
            deadline.text = notice.deadline
        } else {
            deadline.visibility = View.GONE
        }
    }
}