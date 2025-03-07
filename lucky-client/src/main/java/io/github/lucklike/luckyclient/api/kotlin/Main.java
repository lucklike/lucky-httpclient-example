package io.github.lucklike.luckyclient.api.kotlin;

import com.luckyframework.common.DateUtils;
import com.luckyframework.common.StopWatch;
import com.luckyframework.httpclient.proxy.async.AsyncTaskExecutor;
import com.luckyframework.httpclient.proxy.async.JavaThreadAsyncTaskExecutor;
import com.luckyframework.httpclient.proxy.async.KotlinCoroutineAsyncTaskExecutor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
//        int l = 100;
//        AsyncExecutor asyncExecutor = new KotlinCoroutineAsyncExecutor(Executors.newFixedThreadPool(5));
//        CompletableFuture<String>[] futureArray = new CompletableFuture[l];
//        for (int i = 0; i < l; i++) {
//
//            futureArray[i] = asyncExecutor.supplyAsync(() -> {
//                System.out.println(DateUtils.time() + " --> " +Thread.currentThread().getName() );
//                return NanoIdUtils.randomNanoId();
//            });
//        }
//        CompletableFuture.allOf(futureArray).thenRun(() -> {
//            List<String> hendleResultList = Stream.of(futureArray).map(CompletableFuture::join).collect(Collectors.toList());
//            hendleResultList.forEach(System.out::println);
//        }).join();
        printTest();
    }

    private static void printTest() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();

        printLine(new JavaThreadAsyncTaskExecutor(Executors.newFixedThreadPool(5)), stopWatch, "java");
        printLine(new KotlinCoroutineAsyncTaskExecutor(Executors.newFixedThreadPool(5)), stopWatch, "kotlin");


        stopWatch.stopWatch();
        System.out.println(stopWatch.prettyPrintFormat());
    }


    private static void printLine(AsyncTaskExecutor asyncExecutor, StopWatch stopWatch, String taskName) throws InterruptedException {
        int count = 15;
        stopWatch.start(taskName);
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            int j = i;
            asyncExecutor.execute(() -> {
                System.out.println(DateUtils.time() + " --> " +Thread.currentThread().getName() + "--" + j);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        stopWatch.stopLast();
        Executor executor = asyncExecutor.getExecutor();
        if (executor instanceof ExecutorService) {
            ((ExecutorService) executor).shutdown();
        }
    }
}
