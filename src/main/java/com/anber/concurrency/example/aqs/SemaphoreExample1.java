package com.anber.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author anber
 * @date 2018/11/8
 **/
@Slf4j
public class SemaphoreExample1 {

    private static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        //新建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(20);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(()->{
                try {
                    //获取一个许可
                    semaphore.acquire();
                    test(threadNum);
                    //释放一个许可
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception: {}", e);
                }
            });
        }

        log.info("finish");
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}", threadNum);
        Thread.sleep(1000);
    }
}
