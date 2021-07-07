package com.yoochangwons.todolist

import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yoochangwons.todolist.databinding.ActivityTodoListReviewCodeBinding
import com.yoochangwons.todolist.databinding.ItemTodoReviewBinding

class TodoListReviewCode : AppCompatActivity() {

    private lateinit var binding: ActivityTodoListReviewCodeBinding

    private val model: ReviewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoListReviewCodeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerViewReview.apply {
            adapter = TodoRecyclerViewReview(
                emptyList(),
                onClickDeleteIcon = {
                    model.deleteImageButtonReview(it)
                },
                onClickItem = {
                    model.toggleTodoReview(it)
                }
            )
            layoutManager = LinearLayoutManager(this@TodoListReviewCode)
        }

        binding.addButtonReview.setOnClickListener {
            model.addTodoListReview(TodoListReview(binding.editTextReview.text.toString()))
        }

        model.liveDataReview.observe(this, Observer {
            (binding.recyclerViewReview.adapter as TodoRecyclerViewReview).setData(it)
        })
    }
}

data class TodoListReview(val text: String, var isDone: Boolean = false)

class TodoRecyclerViewReview(
    var dataList: List<TodoListReview>,
    val onClickDeleteIcon: (todo: TodoListReview) -> Unit,
    val onClickItem: (todo: TodoListReview) -> Unit
) : RecyclerView.Adapter<TodoRecyclerViewReview.ViewHolder>() {

    class ViewHolder(val binding: ItemTodoReviewBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo_review, parent, false)
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
            onClickDeleteIcon(todo)
        }

        holder.binding.root.setOnClickListener {
            onClickItem(todo)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(newData: List<TodoListReview>) {
        dataList = newData
        notifyDataSetChanged()
    }
}

class ReviewViewModel : ViewModel() {
    val liveDataReview = MutableLiveData<List<TodoListReview>>()

    private val dataTodo = ArrayList<TodoListReview>()

    fun addTodoListReview(todoListReview: TodoListReview) {
        dataTodo.add(todoListReview)
        liveDataReview.value = dataTodo
    }

    fun toggleTodoReview(todo: TodoListReview) {
        todo.isDone = !todo.isDone
        liveDataReview.value = dataTodo
    }

    fun deleteImageButtonReview(todo: TodoListReview) {
        dataTodo.remove(todo)
        liveDataReview.value = dataTodo
    }
}