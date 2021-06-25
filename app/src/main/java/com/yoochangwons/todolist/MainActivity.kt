package com.yoochangwons.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yoochangwons.todolist.databinding.ActivityMainBinding
import com.yoochangwons.todolist.databinding.ItemTodoBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val data = arrayListOf<Todo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        data.add(Todo("숙제"))
        data.add(Todo("청소"))

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = TodoAdapter(data,
            onClickDeleteIcon = {
                deleteTodo(it)
            }
        )

        binding.addButton.setOnClickListener {
            addTodo()
        }
    }

    // 할 일을 추가하는 메서드
    private fun addTodo() {
        val todo = Todo(binding.editText.text.toString())
        data.add(todo)
        // 데이터가 바뀌었을 때 RecyclerView adapter 에게 알려줘야 한다 notifyDataSetChanged 을 사용
        // non-null
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }

    // 할 일을 삭제하는 메서드
    private fun deleteTodo(todo: Todo) {
        data.remove(todo)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }
}

data class Todo(
    val text: String,
    var isDone: Boolean = false
)

class TodoAdapter(
    private val dataSet: List<Todo>,
    val onClickDeleteIcon: (todo: Todo) -> Unit
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
        viewHolder.binding.deleteImageView.setOnClickListener {
            onClickDeleteIcon.invoke(todo)
        }
    }

    override fun getItemCount() = dataSet.size
}