package com.yoochangwonspro.app2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yoochangwonspro.app2.databinding.ItemTodoBinding
import com.yoochangwonspro.app2.model.entity.Todo

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var todoList: List<Todo> = listOf()

    inner class TodoViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(todo: Todo) {
                binding.itemTodoTextView.text = todo.task
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun setData(todoList: List<Todo>) {
        this.todoList = todoList
        notifyDataSetChanged()
    }
}