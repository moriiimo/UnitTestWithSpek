package com.moriiimo.droidkaigi.unittestwithspek.todo.ui.binding

import com.moriiimo.droidkaigi.unittestwithspek.todo.model.Todo


object TodoBindingModelConverter {
  fun convert(list: List<Todo>): List<TodoBindingModel> = list.map {
    convert(it)
  }

  private fun convert(todo: Todo): TodoBindingModel = TodoBindingModel(
      todo.id,
      todo.label
  )
}
