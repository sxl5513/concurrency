package com.anber.concurrency.example.syncContainer;

import com.anber.concurrency.annoations.ThreadSafe;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author anber
 * @date 2018/11/7
 **/
@Slf4j
@ThreadSafe
public class CollectionsExample3 {
    /**
     * 请求总数
     */
    public static int clientTotal = 5000;

    /**
     * 同时并发执行的线程数
     */
    public static int threadTotal = 200;

    public static Map<Integer, Integer> map = Collections.synchronizedMap(Maps.newHashMap());

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
        log.info("size:{}", map.size());
    }

    private static void update(int i) {
        map.put(i, i);

    }
}
