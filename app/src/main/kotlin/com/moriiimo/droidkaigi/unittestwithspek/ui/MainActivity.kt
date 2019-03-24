package com.moriiimo.droidkaigi.unittestwithspek.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.moriiimo.droidkaigi.unittestwithspek.R
import com.moriiimo.droidkaigi.unittestwithspek.ui.todo.TodoListFragment

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction().apply {
        add(
            R.id.container,
            TodoListFragment(),
            TodoListFragment::class.java.canonicalName
        )
      }.commitNow()
    }
  }
}