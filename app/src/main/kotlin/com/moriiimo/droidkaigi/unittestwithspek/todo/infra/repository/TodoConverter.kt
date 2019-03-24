package com.moriiimo.droidkaigi.unittestwithspek.todo.infra.repository

import com.moriiimo.droidkaigi.unittestwithspek.todo.infra.db.TodoEntity
import com.moriiimo.droidkaigi.unittestwithspek.todo.model.Todo

object TodoConverter {
  fun convert(list: List<TodoEntity>): List<Todo> = list.map {
    convert(it)
  }

  fun convert(todoEntity: TodoEntity): Todo = Todo(
      id = todoEntity.id,
      label = todoEntity.name,
      complete = todoEntity.complete,
      deadline = todoEntity.deadline
  )

  fun convertToEntity(list: List<Todo>): List<TodoEntity> = list.map {
    convert(it)
  }

  private fun convert(todo: Todo): TodoEntity = TodoEntity(
      todo.label,
      todo.deadline,
      todo.complete
  )
}