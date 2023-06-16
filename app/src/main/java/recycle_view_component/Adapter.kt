package recycle_view_component

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import data_classes.ToDoItem

class Adapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<ViewHolder>(){
    var notices = listOf<ToDoItem>()

    set(value) {
        val callback = MyDiffUtil(
            oldItems = field,
            newItems = value,
            {oldItem: ToDoItem, newItem -> oldItem.id == newItem.id }
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

        holder.changeButton.setOnClickListener {
            listener.onItemClick(position)
        }
    }
}

interface OnItemClickListener {
    fun onItemClick(position: Int)
}