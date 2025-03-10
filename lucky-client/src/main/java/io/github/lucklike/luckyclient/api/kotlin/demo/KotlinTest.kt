package io.github.lucklike.luckyclient.api.kotlin.demo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

class KotlinTest {

    companion object {


        @OptIn(ExperimentalCoroutinesApi::class)
        @JvmStatic
        fun getCoroutineScope(): CoroutineScope {
            return CoroutineScope(Dispatchers.IO.limitedParallelism(10000))
        }
    }
}