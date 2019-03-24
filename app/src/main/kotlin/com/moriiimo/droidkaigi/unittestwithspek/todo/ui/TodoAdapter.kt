package com.moriiimo.droidkaigi.unittestwithspek.todo.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.moriiimo.droidkaigi.unittestwithspek.todo.ui.binding.TodoBindingModel


class TodoAdapter : ListAdapter<TodoBindingModel, TodoViewHolder>(ITEM_CALL_BACK) {

  companion object {
    private val ITEM_CALL_BACK = object : DiffUtil.ItemCallback<TodoBindingModel>() {
      override fun areItemsTheSame(
          oldItem: TodoBindingModel,
          newItem: TodoBindingModel
      ): Boolean = oldItem.id == newItem.id

      override fun areContentsTheSame(
          oldItem: TodoBindingModel,
          newItem: TodoBindingModel
      ): Boolean = oldItem == newItem
    }
  }

  override fun onCreateViewHolder(
      parent: ViewGroup,
      viewType: Int
  ): TodoViewHolder {
    return TodoViewHolder(parent)
  }

  override fun onBindViewHolder(
      holder: TodoViewHolder,
      position: Int
  ) {
    holder.bind(getItem(position))
  }
}