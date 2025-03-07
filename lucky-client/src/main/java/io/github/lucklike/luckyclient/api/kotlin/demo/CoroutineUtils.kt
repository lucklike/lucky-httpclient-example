package io.github.lucklike.luckyclient.api.kotlin.demo

import kotlinx.coroutines.*
import java.util.concurrent.ExecutorService
import kotlin.system.measureTimeMillis

object CoroutineUtils {
    private val executor: ExecutorService = ThreadPoolProvider.getExecutor()
    private val dispatcher = executor.asCoroutineDispatcher()

    @JvmStatic
    fun runTasks() {
        runBlocking {
            val time = measureTimeMillis {
                val scope = CoroutineScope(Dispatchers.IO)
                repeat(10000) {
                    scope.launch {
                        println("Task running on ${Thread.currentThread().name}")
                    }
                }
            }
            println("Kotlin 协程任务创建时间：$time ms")
        }
    }
}