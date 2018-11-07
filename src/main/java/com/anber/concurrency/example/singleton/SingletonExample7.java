package com.anber.concurrency.example.singleton;

import com.anber.concurrency.annoations.Recommend;
import com.anber.concurrency.annoations.ThreadSafe;

/**
 * 枚举模式: 最安全的
 * @author anber
 * @date 2018/11/7
 **/
@ThreadSafe
@Recommend
public class SingletonExample7 {
    /**
     * 私有构造
     */
    private SingletonExample7() {

    }

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getSingleton();
    }

    private enum Singleton {
        INSTANCE;

        private SingletonExample7 singleton;

        /**
         * JVM保证这个方法绝对只被调用一次
         */
        Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getSingleton() {
            return singleton;
        }

    }
}
