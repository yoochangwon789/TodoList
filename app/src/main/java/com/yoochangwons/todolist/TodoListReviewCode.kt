package com.yoochangwons.todolist

import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yoochangwons.todolist.databinding.ActivityTodoListReviewCodeBinding
import com.yoochangwons.todolist.databinding.ItemTodoReviewBinding

class TodoListReviewCode : AppCompatActivity() {

    private lateinit var binding: ActivityTodoListReviewCodeBinding
    val dataTodo = ArrayList<TodoListReview>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoListReviewCodeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerViewReview.apply {
            adapter = TodoRecyclerViewReview(dataTodo,
                onClickDeleteIcon = {deleteImageButtonReview(it)},
                onClickItem = {toggleTodoReview(it)}
            )
            layoutManager = LinearLayoutManager(this@TodoListReviewCode)
        }

        binding.addButtonReview.setOnClickListener {
            addTodoListReview()
        }
    }

    private fun addTodoListReview() {
        dataTodo.add(TodoListReview(binding.editTextReview.text.toString()))
        binding.recyclerViewReview.adapter?.notifyDataSetChanged()
    }

    private fun toggleTodoReview(todo: TodoListReview) {
        todo.isDone = !todo.isDone
        binding.recyclerViewReview.adapter?.notifyDataSetChanged()
    }

    private fun deleteImageButtonReview(todo: TodoListReview) {
        dataTodo.remove(todo)
        binding.recyclerViewReview.adapter?.notifyDataSetChanged()
    }
}

data class TodoListReview(val text: String, var isDone: Boolean = false)

class TodoRecyclerViewReview(
    val dataList: List<TodoListReview>,
    val onClickDeleteIcon: (todo: TodoListReview) -> Unit,
    val onClickItem: (todo: TodoListReview) -> Unit
) : RecyclerView.Adapter<TodoRecyclerViewReview.ViewHolder>() {

    class ViewHolder(val binding: ItemTodoReviewBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo_review, parent, false)
        return ViewHolder(ItemTodoReviewBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = dataList[position]
        holder.binding.todoTextReview.text = todo.text

        if (dataList[position].isDone) {
            holder.binding.todoTextReview.apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                setTypeface(null, Typeface.ITALIC)
            }
        } else {
            holder.binding.todoTextReview.apply {
                paintFlags = 0
                setTypeface(null, Typeface.NORMAL)
            }
        }

        holder.binding.deleteImageViewReview.setOnClickListener {
            onClickDeleteIcon.invoke(todo)
        }

        holder.binding.root.setOnClickListener {
            onClickItem.invoke(todo)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}