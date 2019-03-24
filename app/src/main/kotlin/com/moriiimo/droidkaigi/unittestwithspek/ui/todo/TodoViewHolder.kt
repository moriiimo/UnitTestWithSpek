package com.moriiimo.droidkaigi.unittestwithspek.ui.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moriiimo.droidkaigi.unittestwithspek.R
import com.moriiimo.droidkaigi.unittestwithspek.ui.todo.binding.TodoBindingModel
import kotlinx.android.synthetic.main.view_todo.view.*

class TodoViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(
    R.layout.view_todo, parent,
    false)) {

  fun bind(todo: TodoBindingModel) {
    itemView.name.text = todo.name
  }
}