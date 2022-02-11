package com.yoochangwonspro.app2.model.repository

import android.app.Application
import android.content.Context
import com.yoochangwonspro.app2.model.TodoDatabaseProvider

class TodoRepository(application: Application) {

    private val todoList = TodoDatabaseProvider.getTodoDatabase(application).todoListDao()
}