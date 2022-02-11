package com.yoochangwonspro.app2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.yoochangwonspro.app2.databinding.ActivityAddTodoBinding
import com.yoochangwonspro.app2.model.entity.Todo
import com.yoochangwonspro.app2.model.repository.TodoRepository
import com.yoochangwonspro.app2.viewmodel.TodoViewModel
import com.yoochangwonspro.app2.viewmodel.TodoViewModelFactory

class AddTodoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTodoBinding

    private lateinit var todoViewModel: TodoViewModel

    private lateinit var todoRepository: TodoRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todoRepository = TodoRepository(application)
        todoViewModel =
            ViewModelProvider(this, TodoViewModelFactory(todoRepository))[TodoViewModel::class.java]

        val id: Long? = null
        val task = binding.addTodoEditText.text.toString()

        binding.addTodoDoneButton.setOnClickListener {
            val todo = Todo(id, task)
            todoViewModel.insert(todo)
            finish()
        }
    }
}