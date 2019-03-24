package com.moriiimo.droidkaigi.unittestwithspek.ui.todo.binding

import com.moriiimo.droidkaigi.unittestwithspek.model.Todo


object TodoBindingModelConverter {
  fun convert(list: List<Todo>): List<TodoBindingModel> = list.map {
    convert(it)
  }

  private fun convert(todo: Todo): TodoBindingModel = TodoBindingModel(
      todo.id,
      todo.label
  )
}
