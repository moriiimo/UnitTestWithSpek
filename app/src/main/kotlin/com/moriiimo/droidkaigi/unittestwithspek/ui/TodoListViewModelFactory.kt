package com.moriiimo.droidkaigi.unittestwithspek.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.Scheduler

class TodoListViewModelFactory(
    private val application: Application,
    private val uiScheduler: Scheduler
) : ViewModelProvider.AndroidViewModelFactory(application) {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return TodoListViewModel(
        application,
        uiScheduler
    ) as T
  }
}