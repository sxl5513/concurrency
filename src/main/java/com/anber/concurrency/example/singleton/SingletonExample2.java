package com.anber.concurrency.example.singleton;

import com.anber.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载时进行创建
 *
 * 不足: 如果构造中存在过多的处理,会导致类加载过慢,可能会引起性能问题
 * 如果用饿汉模式只进行类的加载,没有实际调用的话,会造成资源的浪费
 * @author anber
 * @date 2018/11/7
 **/
@ThreadSafe
public class SingletonExample2 {

    /**
     * 私有构造
     */
    private SingletonExample2() {

    }

    /**
     * 单例
     */
    public static SingletonExample2 example1 = new SingletonExample2();

    /**
     * 静态工厂方法
     * @return
     */
    public static SingletonExample2 getInstance() {
        return example1;
    }
}
