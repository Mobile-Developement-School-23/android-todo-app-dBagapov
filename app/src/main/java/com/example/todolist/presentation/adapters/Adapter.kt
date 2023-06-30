package com.example.todolist.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.models.ToDoItem


class Adapter(private val listener: ItemClickListener) : RecyclerView.Adapter<ViewHolder>(){
    var notices = listOf<ToDoItem>()

        set(value) {
            val callback = MyDiffUtil(
                oldItems = field,
                newItems = value,
                { oldItem: ToDoItem, newItem -> oldItem.id == newItem.id }
            )

            field = value
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(
            R.layout.note,
            parent,
            false
        ))
    }

    override fun getItemCount() = notices.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(notices[position])

        holder.checkBox.setOnClickListener {
            listener.onCheckboxClicked(position)
        }

        holder.changeButton.setOnClickListener {
            listener.onButtonClicked(position)
        }
    }
}

interface ItemClickListener {
    fun onCheckboxClicked(position: Int)
    fun onButtonClicked(position: Int)
}
