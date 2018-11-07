package com.anber.concurrency.example.singleton;


import com.anber.concurrency.annoations.NotRecommend;
import com.anber.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式
 * 单例实例第一次使用时进行创建
 * @author anber
 * @date 2018/11/7
 **/
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    /**
     * 私有构造
     */
    private SingletonExample3() {

    }

    /**
     * 单例
     */
    public static SingletonExample3 example1 = null;

    /**
     * 静态工厂方法
     * @return
     */
    public synchronized static SingletonExample3 getInstance() {
        if (example1 == null) {
            example1 = new SingletonExample3();
        }
        return example1;
    }
}
