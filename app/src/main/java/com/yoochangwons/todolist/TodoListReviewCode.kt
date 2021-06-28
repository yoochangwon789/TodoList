package com.yoochangwons.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoListReviewCode : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list_review_code)

        val dataTodo = ArrayList<TodoListReview>()
        dataTodo.add(TodoListReview("숙제", false))
    }
}

data class TodoListReview(val todo: String, val isDone: Boolean)

class TodoRecyclerViewReview(
    val dataList: List<TodoListReview>
) : RecyclerView.Adapter<TodoRecyclerViewReview.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView

        init {
            textView = itemView.findViewById(R.id.todo_text_review)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo_review, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = dataList[position].toString()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}