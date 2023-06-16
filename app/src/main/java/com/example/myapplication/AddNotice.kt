package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Button
import android.widget.TextView
import android.widget.Switch
import android.widget.DatePicker
import android.view.View
import android.widget.EditText
import java.util.Calendar

import androidx.appcompat.app.AppCompatActivity

class AddNotice : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);

        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val saveButton = findViewById<ImageButton>(R.id.saveButton)
        val backButton = findViewById<ImageButton>(R.id.backButton)

        val textNote = findViewById<EditText>(R.id.newText)
        val switchDeadline = findViewById<Switch>(R.id.switchDeadline)
        val datePicker = findViewById<DatePicker>(R.id.datePicker)
        val textDeadline = findViewById<TextView>(R.id.deadlineText)

        selectDeadline(switchDeadline, datePicker, textDeadline)

        val oldTextNote: String? = intent.getStringExtra("noteText")
        val isCompleted = intent.getBooleanExtra("isCompleted", false)

        if (oldTextNote != null) {
            val isDeadline = intent.getBooleanExtra("isDeadlineEnabled", false)
            val oldDate = intent?.getStringExtra("selectedDate")

            textNote.setText(oldTextNote)
            if (isDeadline) {
                switchDeadline.isChecked = true
                textDeadline.text = oldDate
                datePicker.visibility = View.GONE
                textDeadline.visibility = View.VISIBLE
            }
        }

        deleteButton.setOnClickListener {
            finish()
        }

        backButton.setOnClickListener {
            finish()
        }

        saveButton.setOnClickListener {
            val noteText = textNote.text.toString()
            val isDeadlineEnabled = switchDeadline.isChecked
            val selectedDate = textDeadline.text.toString()

            if (noteText.isEmpty()) {
                finish()
            }

            val resultIntent = Intent()
            resultIntent.putExtra("noteText", noteText)
            resultIntent.putExtra("isDeadlineEnabled", isDeadlineEnabled)
            resultIntent.putExtra("selectedDate", selectedDate)
            resultIntent.putExtra("isCompleted", isCompleted)

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

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
