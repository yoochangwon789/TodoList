package com.yoochangwonspro.app2.model.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.yoochangwonspro.app2.model.TodoDatabaseProvider
import com.yoochangwonspro.app2.model.entity.Todo

class TodoRepository(application: Application) {

    private val todoList = TodoDatabaseProvider.getTodoDatabase(application).todoListDao()

    fun getAll(): LiveData<List<Todo>> {
        return todoList.getAll()
    }

    fun insert(todo: Todo) {
        todoList.insert(todo)
    }
}