package com.yoochangwons.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yoochangwons.todolist.databinding.ActivityTodoListReviewCodeBinding
import com.yoochangwons.todolist.databinding.ItemTodoBinding
import com.yoochangwons.todolist.databinding.ItemTodoReviewBinding

class TodoListReviewCode : AppCompatActivity() {

    private lateinit var binding: ActivityTodoListReviewCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoListReviewCodeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val dataTodo = ArrayList<TodoListReview>()
        dataTodo.add(TodoListReview("숙제", false))
        dataTodo.add(TodoListReview("청소", false))

        binding.recyclerViewReview.adapter = TodoRecyclerViewReview(dataTodo)
        binding.recyclerViewReview.layoutManager = LinearLayoutManager(this)
    }
}

data class TodoListReview(val todo: String, val isDone: Boolean)

class TodoRecyclerViewReview(
    val dataList: List<TodoListReview>
) : RecyclerView.Adapter<TodoRecyclerViewReview.ViewHolder>() {

    class ViewHolder(val binding: ItemTodoReviewBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo_review, parent, false)
        return ViewHolder(ItemTodoReviewBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.todoTextReview.text = dataList[position].todo
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}