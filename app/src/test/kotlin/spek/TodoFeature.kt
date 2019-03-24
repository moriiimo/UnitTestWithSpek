package spek

import com.moriiimo.droidkaigi.unittestwithspek.todo.model.Todo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TodoFeature : Spek({

  Feature("Todo") {

    val now = System.currentTimeMillis()

    lateinit var todo: Todo

    Scenario("complete status") {
      Given("the todo is not complete") {
        todo = Todo(
            id = 1,
            label = "",
            deadline = now,
            complete = false
        )
      }

      When("complete the todo") {
        todo.checkComplete()
      }

      Then("the todo is complete") {
        assertTrue(todo.complete)
      }
    }

    Scenario("deadline") {
      Given("the todo is not complete") {
        todo = Todo(
            id = 1,
            label = "",
            deadline = now,
            complete = false
        )
      }

      When("add the deadline by 3days") {
        todo.addDayToDeadline(3)
      }

      Then("the deadline is after 3days later") {
        val expect = Calendar.getInstance(TimeZone.getDefault(), Locale.JAPAN).apply {
          timeInMillis = now
          add(Calendar.DATE, 3)
        }
        assertEquals(expect.timeInMillis, todo.deadline)
      }

      When("reduce the deadline three days") {
        todo.reduceDayToDeadline(3)
      }

      Then("the deadline is 3days ago") {
        assertEquals(now, todo.deadline)
      }
    }
  }
})