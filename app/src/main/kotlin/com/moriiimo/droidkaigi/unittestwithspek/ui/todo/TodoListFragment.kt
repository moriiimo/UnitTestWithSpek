package com.moriiimo.droidkaigi.unittestwithspek.ui.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.moriiimo.droidkaigi.unittestwithspek.R
import io.reactivex.android.schedulers.AndroidSchedulers

class TodoListFragment : Fragment() {

  private lateinit var adapter: TodoAdapter

  private lateinit var todoListViewModelFactory: TodoListViewModelFactory

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    val application = activity?.application
    application ?: return

    todoListViewModelFactory = TodoListViewModelFactory(
        application,
        AndroidSchedulers.mainThread()
    )

    val viewModel = ViewModelProviders.of(
        this,
        todoListViewModelFactory
    ).get(TodoListViewModel::class.java)

    viewModel.todoListLiveData.observe(
        this,
        Observer {
          adapter.submitList(it)
        }
    )
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? = inflater.inflate(
      R.layout.fragment_todo_list,
      container,
      false
  )

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    view.findViewById<RecyclerView>(R.id.recycler_view).apply {
      setHasFixedSize(true)
      adapter = TodoAdapter().also {
        this@TodoListFragment.adapter = it
      }
    }
  }
}