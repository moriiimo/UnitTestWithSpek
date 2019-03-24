package com.moriiimo.droidkaigi.unittestwithspek.infra.repository.todo

import com.moriiimo.droidkaigi.unittestwithspek.infra.db.TodoEntity
import com.moriiimo.droidkaigi.unittestwithspek.model.Todo

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
    TodoConverter.convert(it)
  }

  private fun convert(todo: Todo): TodoEntity = TodoEntity(
      todo.label,
      todo.deadline,
      todo.complete
  )
}