package spek

import android.app.Application
import androidx.lifecycle.Observer
import com.moriiimo.droidkaigi.unittestwithspek.ui.todo.TodoListViewModel
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.schedulers.Schedulers
import org.mockito.Mockito
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import useLiveData

class TodoListViewModelSpec : Spek({

  useLiveData()

  val application: Application by lazy {
    Mockito.mock(Application::class.java)
  }

  val todoListViewModel: TodoListViewModel by lazy {
    TodoListViewModel(
        application,
        Schedulers.trampoline()
    )
  }

  Feature("TodoViewModel") {
    Scenario("Todoの一覧フィルターするとLiveDataに新しい値が入る") {

      val observer = mock<Observer<String>>()

      lateinit var filterWord: String

      Given("LiveDataがアクティブになる") {
        todoListViewModel.wordLiveData.observeForever(observer)
      }
      When("ViewModelのフィルターメソッドを実行する") {
        filterWord = "Spek"
        todoListViewModel.filter(filterWord)
      }
      Then("LiveDataのvalueがSpekになる") {
        Mockito.verify(observer).onChanged(filterWord)
      }
    }
  }
})