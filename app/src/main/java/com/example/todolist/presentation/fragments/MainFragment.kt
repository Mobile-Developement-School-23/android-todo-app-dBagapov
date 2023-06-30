package com.example.todolist.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.repository.ToDoItemsRepository
import com.example.todolist.presentation.adapters.Adapter
import com.example.todolist.presentation.adapters.ItemDecoration
import com.example.todolist.presentation.adapters.ItemClickListener
import kotlinx.coroutines.launch


class MainFragment : Fragment(), ItemClickListener {
    private lateinit var addButton: ImageButton
    private lateinit var noticesRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        noticesRecyclerView = rootView.findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        val noticesAdapter = Adapter(this)
        noticesRecyclerView.adapter = noticesAdapter
        noticesRecyclerView.layoutManager = layoutManager
        lifecycleScope.launch {
            noticesAdapter.notices = ToDoItemsRepository.getNotices()
        }
        noticesRecyclerView.addItemDecoration(ItemDecoration(bottomOffset = 40f.toInt()))

        noticesRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                layoutManager.orientation
            )
        )

        noticesRecyclerView.addItemDecoration(
            ItemDecoration(
                bottomOffset = 16f.toInt(),
                topOffset = 16f.toInt()
            )
        )

        addButton = rootView.findViewById(R.id.addButton)
        addButton.setOnClickListener {
            val addFragment = AddNoticeFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, addFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return rootView
    }

    override fun onCheckboxClicked(position: Int) {
        lifecycleScope.launch {
            ToDoItemsRepository.get(position).isCompleted = !ToDoItemsRepository.get(position).isCompleted
        }
    }


    override fun onButtonClicked(position: Int) {
        val addFragment = AddNoticeFragment()

        lifecycleScope.launch {
            val id = ToDoItemsRepository.get(position).id

            val bundle = Bundle().apply {
                putString("notice id", id)
            }

            addFragment.arguments = bundle

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, addFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}


