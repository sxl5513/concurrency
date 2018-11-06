package com.anber.concurrency.example.atomic;

import com.anber.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author anber
 * @date 2018/11/6
 **/
@Slf4j
@ThreadSafe
public class AtomicExample5 {

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {
        AtomicExample5 atomicExample5 = new AtomicExample5();
        if (updater.compareAndSet(atomicExample5, 100, 120)) {
            log.info("update success1,{}", atomicExample5.getCount());
        }

        if (updater.compareAndSet(atomicExample5, 100, 500)) {
            log.info("update success2,{}", atomicExample5.getCount());
        } else {
            log.info("update failed,{}", atomicExample5.getCount());
        }
    }
}
