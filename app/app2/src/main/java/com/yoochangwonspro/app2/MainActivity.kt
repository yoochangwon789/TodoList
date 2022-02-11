package com.yoochangwonspro.app2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.yoochangwonspro.app2.databinding.ActivityMainBinding
import com.yoochangwonspro.app2.model.repository.TodoRepository
import com.yoochangwonspro.app2.viewmodel.TodoViewModel
import com.yoochangwonspro.app2.viewmodel.TodoViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var todoViewModel: TodoViewModel

    private lateinit var todoRepository: TodoRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todoRepository = TodoRepository(application)
        todoViewModel =
            ViewModelProvider(this, TodoViewModelFactory(todoRepository))[TodoViewModel::class.java]

        binding.addTodoButton.setOnClickListener {
            startActivity(
                Intent(this, AddTodoActivity::class.java)
            )
        }
    }
}