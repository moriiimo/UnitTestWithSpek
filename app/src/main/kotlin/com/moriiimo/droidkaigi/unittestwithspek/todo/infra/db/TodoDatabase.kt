package com.moriiimo.droidkaigi.unittestwithspek.todo.infra.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [TodoEntity::class],
    version = 1
)
abstract class TodoDatabase : RoomDatabase() {

  abstract fun todoDao(): TodoDao

  companion object {

    private var instance: TodoDatabase? = null

    fun getInstance(context: Context): TodoDatabase =
        instance ?: synchronized(this) {
          instance ?: buildDatabase(context).also {
            instance = it
          }
        }

    private fun buildDatabase(context: Context): TodoDatabase =
        Room.databaseBuilder(
            context,
            TodoDatabase::class.java,
            TodoDatabase::class.java.simpleName
        ).build()
  }
}