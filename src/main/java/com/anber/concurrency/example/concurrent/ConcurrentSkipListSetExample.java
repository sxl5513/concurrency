package com.anber.concurrency.example.concurrent;

import com.anber.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.*;

/**
 * @author anber
 * @date 2018/11/7
 **/
@Slf4j
@ThreadSafe
public class ConcurrentSkipListSetExample {
    /**
     * 请求总数
     */
    public static int clientTotal = 5000;

    /**
     * 同时并发执行的线程数
     */
    public static int threadTotal = 200;

    public static Set<Integer> set = new ConcurrentSkipListSet<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        //信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //计数器
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            threadPool.execute(()->{
                try {
                    semaphore.acquire();
                    update(count);
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
        log.info("size:{}", set.size());
    }

    private static void update(int i) {
        set.add(i);
    }
}
