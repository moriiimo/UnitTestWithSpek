package junit

import com.moriiimo.droidkaigi.unittestwithspek.todo.model.Todo
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TodoTest {

  private val now = System.currentTimeMillis()

  private lateinit var todo: Todo

  @Before
  fun setup() {
    todo = Todo(id = 1, label = "Write Unit Test with Spek", complete = false, deadline = now)
  }

  @Test
  fun changeCompleteStatus_shouldBeTrue() {
    todo.checkComplete()
    assertTrue(todo.complete)
  }

  @Test
  fun changeCompleteStatus_shouldBeFalse() {
    todo.unCheckComplete()
    assertFalse(todo.complete)
  }

  @Test
  fun addDaysToDeadline_shouldBeThreeDaysLater() {
    val expect = Calendar.getInstance(TimeZone.getDefault(), Locale.JAPAN).apply {
      timeInMillis = now
      add(Calendar.DATE, 3)
    }

    todo.addDayToDeadline(3)

    assertEquals(expect.timeInMillis, todo.deadline)
  }

  @Test
  fun reduceDaysToDeadline_shouldBeThreeDaysAgo() {
    val expect = Calendar.getInstance(TimeZone.getDefault(), Locale.JAPAN).apply {
      timeInMillis = now
      add(Calendar.DATE, -3)
    }

    todo.reduceDayToDeadline(3)

    assertEquals(expect.timeInMillis, todo.deadline)
  }
}