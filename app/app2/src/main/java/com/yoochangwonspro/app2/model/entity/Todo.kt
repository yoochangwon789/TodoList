package com.yoochangwonspro.app2.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,

    @ColumnInfo(name = "task")
    val task: String
)
