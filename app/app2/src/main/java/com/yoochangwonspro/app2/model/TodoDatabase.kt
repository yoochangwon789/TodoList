package com.yoochangwonspro.app2.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yoochangwonspro.app2.model.dao.TodoListDao
import com.yoochangwonspro.app2.model.entity.Todo

@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoListDao(): TodoListDao
}