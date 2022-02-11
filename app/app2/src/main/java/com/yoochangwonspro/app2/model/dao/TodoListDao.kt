package com.yoochangwonspro.app2.model.dao

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yoochangwonspro.app2.model.entity.Todo

@Dao
interface TodoListDao {

    @Query("SELECT * FROM todo")
    fun getAll(): MutableLiveData<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo: Todo)
}