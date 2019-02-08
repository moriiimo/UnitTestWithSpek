package com.moriiimo.droidkaigi.unittestwithspek.ui

import androidx.lifecycle.MutableLiveData
import com.moriiimo.droidkaigi.unittestwithspek.infra.repository.TodoRepository
import com.moriiimo.droidkaigi.unittestwithspek.ui.binding.TodoBindingModel
import com.moriiimo.droidkaigi.unittestwithspek.ui.binding.TodoBindingModelConverter
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class TodoListLiveData(
    private val todoRepository: TodoRepository,
    private val uiScheduler: Scheduler
) : MutableLiveData<List<TodoBindingModel>>() {

  private val disposable = CompositeDisposable()

  override fun onActive() {
    super.onActive()
    todoRepository
        .getTodo()
        .observeOn(uiScheduler)
        .subscribeBy(
            onSuccess = {
              value = TodoBindingModelConverter.convert(it)
            },
            onError = {
              // nop
            }
        )
  }

  override fun onInactive() {
    disposable.clear()
    super.onInactive()
  }
}