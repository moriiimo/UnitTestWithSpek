package spek

import android.content.Context
import androidx.room.Room
import com.moriiimo.droidkaigi.unittestwithspek.infra.db.TodoDatabase
import com.moriiimo.droidkaigi.unittestwithspek.infra.db.TodoEntity
import io.reactivex.Single
import org.dbtools.android.room.jdbc.JdbcSQLiteOpenHelperFactory
import org.mockito.Mockito
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import useLiveData

class TodoDaoSpec : Spek({

  useLiveData()

  val context: Context by lazy {
    Mockito.mock(Context::class.java)
  }

  val todoDatabase: TodoDatabase by lazy {
    Room.inMemoryDatabaseBuilder(
        context,
        TodoDatabase::class.java
    ).allowMainThreadQueries()
        .openHelperFactory(JdbcSQLiteOpenHelperFactory())
        .build()
  }

  Feature("TodoDao") {

    lateinit var result: Single<Int>

    Scenario("TodoDatabase") {
      Given("two todos have already been registered") {
        val todoList = listOf(
            TodoEntity(name = "SpekでUnitTestを書く", complete = false, deadline = System.currentTimeMillis()),
            TodoEntity(name = "JUnitでUnitTestを書く", complete = false, deadline = System.currentTimeMillis())
        )
        todoDatabase.todoDao().upsertAll(todoList)
      }

      When("count todo") {
        result = todoDatabase.todoDao().count()
      }

      Then("it should be 2") {
        result.test().await().assertValue(2)
      }
    }
  }
})