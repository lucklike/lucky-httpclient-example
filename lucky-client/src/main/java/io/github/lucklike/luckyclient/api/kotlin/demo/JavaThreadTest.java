package io.github.lucklike.luckyclient.api.kotlin.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public class JavaThreadTest {
    public static void main(String[] args) throws InterruptedException {
//        test();
        CoroutineUtils.runTasks();
    }

    private static void test() throws InterruptedException {
        ExecutorService executor = ThreadPoolProvider.getExecutor();
        long startTime = System.currentTimeMillis();

        int count = 1000000;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            executor.submit(() -> {
                System.out.println("Task running on " + Thread.currentThread().getName());
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("Java 线程任务创建时间：" + (endTime - startTime) + " ms");
        ThreadPoolProvider.shutdown();
    }
}
