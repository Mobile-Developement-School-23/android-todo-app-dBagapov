package com.example.todolist.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.todolist.R
import com.example.todolist.data.models.Importance
import com.example.todolist.data.models.ToDoItem
import com.example.todolist.data.repository.ToDoItemsRepository
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

class AddNoticeFragment : Fragment() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_add_notice, container, false)
        val radioGroup: RadioGroup = rootView.findViewById(R.id.radio_group)
        val fragmentManager = requireActivity().supportFragmentManager
        val deleteButton = rootView.findViewById<Button>(R.id.deleteButton)
        val saveButton = rootView.findViewById<ImageButton>(R.id.saveButton)
        val backButton = rootView.findViewById<ImageButton>(R.id.backButton)
        val textNote = rootView.findViewById<EditText>(R.id.newText)
        val switchDeadline = rootView.findViewById<Switch>(R.id.switchDeadline)
        val datePicker = rootView.findViewById<DatePicker>(R.id.datePicker)
        val textDeadline = rootView.findViewById<TextView>(R.id.deadlineText)
        selectDeadline(switchDeadline, datePicker, textDeadline)

        val noteId = arguments?.getString("notice id")
        if (noteId != null) {
            textNote.text = Editable.Factory.getInstance().newEditable("snfoejfes")
            lifecycleScope.launch {
                val note = ToDoItemsRepository.getById(noteId)
                if (note != null) {
                    textNote.text = Editable.Factory.getInstance().newEditable(note.text)
                    if (note.deadlineFlag == true) {
                        switchDeadline.isChecked = true
                        textDeadline.text = note.deadline
                        datePicker.visibility = View.GONE
                        textDeadline.visibility = View.VISIBLE
                    }
                }
            }
        }

        deleteButton.setOnClickListener {
            if (noteId != null) {
                lifecycleScope.launch {
                    ToDoItemsRepository.deleteById(noteId)
                }
            }
            fragmentManager.popBackStack()
        }

        backButton.setOnClickListener {
            fragmentManager.popBackStack()
        }

        saveButton.setOnClickListener {
            if (textNote.text.toString().isEmpty()) {
                fragmentManager.popBackStack()
            } else {
                lifecycleScope.launch {
                    val checkedRadioButtonId: Int = radioGroup.checkedRadioButtonId
                    var id = ToDoItemsRepository.getNewId()
                    var isCompleted = false
                    val text = textNote.text.toString()
                    val deadlineFlag = switchDeadline.isChecked
                    var dateCreated = Date()
                    val deadline = textDeadline.text.toString()
                    val importance: Importance

                    importance = if (checkedRadioButtonId == 1) {
                        Importance.LOW
                    } else if (checkedRadioButtonId == 2) {
                        Importance.COMMON
                    } else {
                        Importance.HIGH
                    }

                    if (noteId != null) {
                        id = noteId
                        val note = ToDoItemsRepository.getById(id)
                        if (note != null) {
                            isCompleted = note.isCompleted
                            dateCreated = note.dateCreated
                        }
                    }

                    val newNote = ToDoItem(
                        id,
                        isCompleted,
                        text,
                        deadlineFlag,
                        dateCreated,
                        deadline,
                        importance
                    )
                    ToDoItemsRepository.addNotice(newNote)

                    fragmentManager.popBackStack()
                }
            }
        }

        return rootView
    }

    private fun selectDeadline(
        @SuppressLint("UseSwitchCompatOrMaterialCode") switchDeadline: Switch,
        datePicker: DatePicker,
        textDeadline: TextView) {

        switchDeadline.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                datePicker.visibility = View.VISIBLE
            } else {
                datePicker.visibility = View.GONE
                textDeadline.visibility = View.GONE
            }
        }

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        datePicker.init(year, month, dayOfMonth)
        { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "$selectedDay.$selectedMonth.$selectedYear"
            textDeadline.text = selectedDate
            textDeadline.visibility = View.VISIBLE
            datePicker.visibility = View.GONE
        }
    }

}