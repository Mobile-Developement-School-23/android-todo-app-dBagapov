package com.example.todolist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.R
import com.example.todolist.presentation.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainFragment = MainFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, mainFragment)
            .commit()
    }
}