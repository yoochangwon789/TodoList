package com.yoochangwonspro.app2.model

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

object TodoDatabaseProvider {

    const val TODO_DATABASE_NAME = "todo_database_name"

    fun getTodoDatabase(context: Context) = Room.databaseBuilder(
        context,
        TodoDatabase::class.java,
        TODO_DATABASE_NAME
    ).build()
}