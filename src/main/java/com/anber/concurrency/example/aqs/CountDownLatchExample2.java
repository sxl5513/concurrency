package com.anber.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author anber
 * @date 2018/11/8
 **/
@Slf4j
public class CountDownLatchExample2 {

    private static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        //新建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        //建立计数器
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(()->{
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    log.error("exception: {}", e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("finish");
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}", threadNum);
    }
}
