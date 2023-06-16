package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import data_classes.ToDoItem
import data_classes.ToDoItemsRepository
import recycle_view_component.Adapter
import recycle_view_component.ItemDecoration
import recycle_view_component.OnItemClickListener

class MainActivity : AppCompatActivity() {
    private val codeRequest = 1
    private lateinit var addButton: ImageButton
    lateinit var noticesRecyclerView: RecyclerView
    private val noticeRepository = ToDoItemsRepository()
    private val noticesAdapter = Adapter(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                // Обработка нажатия кнопки на заметке из списка
                goToChangeNoticeActivity(noticeRepository[position])
            }
        })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noticesRecyclerView = findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        noticesRecyclerView.adapter = noticesAdapter
        noticesRecyclerView.layoutManager = layoutManager
        noticesAdapter.notices = noticeRepository.getNotices(this)

        noticesRecyclerView.addItemDecoration(ItemDecoration(bottomOffset = 40f.toInt()))

        noticesRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                layoutManager.orientation
            )
        )

        noticesRecyclerView.addItemDecoration(
            ItemDecoration(
                bottomOffset = 16f.toInt(),
                topOffset = 16f.toInt()
            )
        )

        addButton = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            goToAddNoticeActivity()
        }
    }

    private fun goToAddNoticeActivity() {
        val intent = Intent(this, AddNotice::class.java)
        startActivityForResult(intent, codeRequest)
    }

    private fun goToChangeNoticeActivity(notice: ToDoItem) {
        val noteText = notice.text
        val isDeadlineEnabled = notice.deadlineFlag
        val date = notice.deadline
        val isCompleted = notice.isCompleted

        val intent = Intent(this, AddNotice::class.java)

        intent.putExtra("noteText", noteText)
        intent.putExtra("isDeadlineEnabled", isDeadlineEnabled)
        intent.putExtra("selectedDate", date)
        intent.putExtra("isComplete", isCompleted)

        startActivityForResult(intent, codeRequest)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == codeRequest && resultCode == Activity.RESULT_OK) {
            val noteText = data?.getStringExtra("noteText")
            val isDeadlineEnabled = data?.getBooleanExtra("isDeadlineEnabled", false)
            val selectedDate = data?.getStringExtra("selectedDate")
            var isCompleted = data?.getBooleanExtra("isCompleted", false)
            if (isCompleted == null) {
                isCompleted = false
            }

            val newNote = ToDoItem(noticeRepository.getSize().toString(), isCompleted, noteText, isDeadlineEnabled, "sfmew", selectedDate.toString())
            noticeRepository.addNotice(newNote)

            noticesAdapter.notices = noticeRepository.getNotices(this)
        }
    }
}
