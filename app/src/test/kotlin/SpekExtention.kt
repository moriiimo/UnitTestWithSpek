import androidx.arch.core.executor.TaskExecutor
import org.spekframework.spek2.dsl.GroupBody

fun GroupBody.useLiveData() {
  androidx.arch.core.executor.ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
    override fun executeOnDiskIO(runnable: Runnable) {
      runnable.run()
    }

    override fun isMainThread(): Boolean {
      return true
    }

    override fun postToMainThread(runnable: Runnable) {
      runnable.run()
    }
  })
}