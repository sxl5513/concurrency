package com.anber.concurrency.example.singleton;

import com.anber.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉模式
 * 单例第一次使用时进行创建
 * @author anber
 * @date 2018/11/7
 **/
@NotThreadSafe
public class SingletonExample1 {

    /**
     * 私有构造
     */
    private SingletonExample1() {

    }

    /**
     * 单例
     */
    public static SingletonExample1 example1 = null;

    /**
     * 静态工厂方法
     * @return
     */
    public static SingletonExample1 getInstance() {
        if (example1 == null) {
            example1 = new SingletonExample1();
        }
        return example1;
    }
}
