package com.anber.concurrency.example.singleton;

import com.anber.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载时进行创建
 *
 * @author anber
 * @date 2018/11/7
 **/
@ThreadSafe
public class SingletonExample6 {

    /**
     * 私有构造
     */
    private SingletonExample6() {

    }

    /**
     * 单例
     */
    public static SingletonExample6 example1 = null;

    static {
        example1 = new SingletonExample6();
    }



    /**
     * 静态工厂方法
     * @return
     */
    public static SingletonExample6 getInstance() {
        return example1;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}
