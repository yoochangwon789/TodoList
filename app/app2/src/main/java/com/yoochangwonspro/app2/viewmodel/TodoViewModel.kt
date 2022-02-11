package com.yoochangwonspro.app2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yoochangwonspro.app2.model.entity.Todo
import com.yoochangwonspro.app2.model.repository.TodoRepository

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {

    private val todoList = repository.getAll()

    fun getAll(): LiveData<List<Todo>> {
        return todoList
    }

    fun insert(todo: Todo) {
        repository.insert(todo)
    }
}