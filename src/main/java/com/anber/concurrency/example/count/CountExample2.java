package com.anber.concurrency.example.count;

import com.anber.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author anber
 * @date 2018/11/6
 **/
@Slf4j
@ThreadSafe
public class CountExample2 {

    /**
     * 请求总数
     */
    public static int clientTotal = 5000;

    /**
     * 同时并发执行的线程数
     */
    public static int threadTotal = 200;

    /**
     * 计数初始值
     */
    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        //信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //计数器
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            threadPool.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        //等待计数器完成
        countDownLatch.await();
        //关闭线程池
        threadPool.shutdown();
        log.info("count{}", count.get());
    }

    private static void add() {
        //先做增加后获取当前的值
        count.incrementAndGet();
        //先获取当前的值后增加
//        count.getAndIncrement();
    }
}
