package com.moriiimo.droidkaigi.unittestwithspek.todo.infra.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class TodoEntity(
    val name: String,
    val deadline: Long,
    val complete: Boolean
) {
  @PrimaryKey(autoGenerate = true)
  var id: Int = 0
}