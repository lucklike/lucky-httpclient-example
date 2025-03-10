package io.github.lucklike.luckyclient.api.kotlin.demo;

import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

public class Test {

    public static void main(String[] args) {
        KotlinCoroutineAsyncTaskExecutor executor = KotlinCoroutineAsyncTaskExecutor.createByConcurrency(3);
        System.out.println(executor);
    }
}

