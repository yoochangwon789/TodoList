package com.yoochangwons.todolist

import android.graphics.Paint
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yoochangwons.todolist.databinding.ActivityMainBinding
import com.yoochangwons.todolist.databinding.ItemTodoBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = TodoAdapter(
                viewModel.data
                ,
                onClickDeleteIcon = {
                    viewModel.deleteTodo(it)
                    binding.recyclerView.adapter?.notifyDataSetChanged()
                },
                onClickItem = {
                    viewModel.toggleTodo(it)
                    binding.recyclerView.adapter?.notifyDataSetChanged()
                }
            )
        }

        binding.addButton.setOnClickListener {
            val todo = Todo(binding.editText.text.toString())
            viewModel.addTodo(todo)
            binding.recyclerView.adapter?.notifyDataSetChanged()
        }
    }

//    private fun toggleTodo(todo: Todo) {
//        todo.isDone = !todo.isDone
//        binding.recyclerView.adapter?.notifyDataSetChanged()
//    }
//
//    // 할 일을 추가하는 메서드
//    private fun addTodo() {
//        val todo = Todo(binding.editText.text.toString())
//        data.add(todo)
//        // 데이터가 바뀌었을 때 RecyclerView adapter 에게 알려줘야 한다 notifyDataSetChanged 을 사용
//        // non-null
//        binding.recyclerView.adapter?.notifyDataSetChanged()
//    }
//
//    // 할 일을 삭제하는 메서드
//    private fun deleteTodo(todo: Todo) {
//        data.remove(todo)
//        binding.recyclerView.adapter?.notifyDataSetChanged()
//    }
}

data class Todo(
    val text: String,
    var isDone: Boolean = false
)

class TodoAdapter(
    private val dataSet: List<Todo>,
    val onClickDeleteIcon: (todo: Todo) -> Unit,
    val onClickItem: (todo: Todo) -> Unit
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_todo, viewGroup, false)

        return TodoViewHolder(ItemTodoBinding.bind(view))
    }

    override fun onBindViewHolder(viewHolder: TodoViewHolder, position: Int) {
        val todo = dataSet[position]
        viewHolder.binding.todoText.text = todo.text

        // isDone 의 값이 true 와 false 때 조건
        // true 같은 경우 paint 를 사용해서 사선을 그어준다
        if (todo.isDone) {
            viewHolder.binding.todoText.apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                // 이텔릭채로 변경하는 코드
                setTypeface(null, Typeface.ITALIC)
            }
        } else {
            viewHolder.binding.todoText.apply {
                // paintFlags 을 0 으로 주게 되면 기존 일반 글자로 선택
                paintFlags = 0
                // NORMAL 글씨로 변경하는 코드
                setTypeface(null, Typeface.NORMAL)
            }
        }

        viewHolder.binding.deleteImageView.setOnClickListener {
            onClickDeleteIcon.invoke(todo)
        }

        viewHolder.binding.root.setOnClickListener {
            onClickItem.invoke(todo)
        }
    }

    override fun getItemCount() = dataSet.size
}

class MainViewModel: ViewModel() {
    val data = arrayListOf<Todo>()

    fun toggleTodo(todo: Todo) {
        todo.isDone = !todo.isDone
    }

    fun addTodo(todo:Todo) {
        data.add(todo)
    }

    fun deleteTodo(todo: Todo) {
        data.remove(todo)
    }
}