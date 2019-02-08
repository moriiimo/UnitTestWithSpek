package com.moriiimo.droidkaigi.unittestwithspek.infra.repository

import android.content.Context
import com.moriiimo.droidkaigi.unittestwithspek.infra.db.TodoDatabase
import com.moriiimo.droidkaigi.unittestwithspek.model.Todo
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class TodoRepository(
    private val todoDatabase: TodoDatabase,
    private val ioScheduler: Scheduler
) {

  companion object {

    private var instance: TodoRepository? = null

    fun getInstance(context: Context): TodoRepository =
        instance ?: synchronized(this) {
          instance ?: TodoRepository(
              todoDatabase = TodoDatabase.getInstance(context),
              ioScheduler = Schedulers.io()
          ).also {
            instance = it
          }
        }
  }

  fun getTodo(): Single<List<Todo>> = todoDatabase
      .todoDao()
      .count()
      .flatMapCompletable {
        if (it == 0) {
          addTodo(
              listOf(
                  Todo(id = 1, label = "SpekでUnitTestを書く", complete = false, deadline = System.currentTimeMillis()),
                  Todo(id = 1, label = "JUnitでUnitTestを書く", complete = false, deadline = System.currentTimeMillis())
              )
          )
        } else {
          Completable.complete()
        }
      }.andThen(
          todoDatabase
              .todoDao()
              .selectAll()
      ).map {
        TodoConverter.convert(it)
      }.subscribeOn(ioScheduler)

  fun getTodo(word: String): Single<List<Todo>> = todoDatabase
      .todoDao()
      .selectAllByWord(word)
      .map {
        TodoConverter.convert(it)
      }.subscribeOn(ioScheduler)

  fun getTodo(id: Int): Single<Todo> = todoDatabase
      .todoDao()
      .selectById(id)
      .map {
        TodoConverter.convert(it)
      }
      .subscribeOn(ioScheduler)

  private fun addTodo(list: List<Todo>): Completable = Completable.fromAction {
    todoDatabase
        .todoDao()
        .upsertAll(
            TodoConverter.convertToEntity(list)
        )
  }.subscribeOn(ioScheduler)

  fun addTodo(todo: Todo): Completable = Completable.complete()
}