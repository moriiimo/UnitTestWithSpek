package com.moriiimo.droidkaigi.unittestwithspek.ui.todo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.moriiimo.droidkaigi.unittestwithspek.infra.repository.todo.TodoRepository
import io.reactivex.Scheduler

class TodoListViewModel(
    application: Application,
    uiScheduler: Scheduler
) : AndroidViewModel(application) {

  val wordLiveData = MutableLiveData<String>()

  val todoListLiveData = TodoListLiveData(
      TodoRepository.getInstance(application),
      uiScheduler
  )

  fun filter(word: String) {
    wordLiveData.value = word
  }
}