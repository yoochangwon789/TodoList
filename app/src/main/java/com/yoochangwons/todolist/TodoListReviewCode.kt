package com.yoochangwons.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TodoListReviewCode : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list_review_code)
    }
}

data class TodoListReview(val todo: String, val isDone: Boolean)

