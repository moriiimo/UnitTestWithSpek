package com.moriiimo.droidkaigi.unittestwithspek.todo.model

import java.util.*

class Todo(val id: Int) {

  constructor(id: Int, label: String, complete: Boolean, deadline: Long) : this(id) {
    this.label = label
    this.complete = complete
    this.deadline = deadline
  }

  var label: String = ""
    private set

  var complete: Boolean = false
    private set

  var deadline: Long = System.currentTimeMillis()
    private set

  fun checkComplete() {
    complete = true
  }

  fun unCheckComplete() {
    complete = false
  }

  fun addDayToDeadline(numOfDays: Int) {
    changeDeadline(numOfDays)
  }

  fun reduceDayToDeadline(numOfDays: Int) {
    changeDeadline(-numOfDays)
  }

  private fun changeDeadline(numOfDays: Int) {
    deadline = Calendar.getInstance(TimeZone.getDefault(), Locale.JAPAN).apply {
      timeInMillis = deadline
      add(Calendar.DATE, numOfDays)
    }.timeInMillis
  }
}