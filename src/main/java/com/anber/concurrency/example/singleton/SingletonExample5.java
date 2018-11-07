package com.anber.concurrency.example.singleton;


import com.anber.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式 -> 双重同步锁单例模式
 * 单例实例第一次使用时进行创建
 * @author anber
 * @date 2018/11/7
 **/
@ThreadSafe
public class SingletonExample5 {

    /**
     * 私有构造
     */
    private SingletonExample5() {

    }

    /**
     * 使用volatile + 双重检测机制 禁止指令重排
     * 单例
     */
    public volatile static SingletonExample5 example1 = null;

    /**
     * 静态工厂方法
     *
     * example1 = new SingletonExample4();执行这步骤,在CPU中进行了三个步骤
     * 1.memory = allocate() 分配对象的内存空间
     * 2.ctorInstance() 初始化对象
     * 3.instance = memory 设置instance指向刚分配的内存
     *
     * @return
     */
    public static SingletonExample5 getInstance() {
        //双重检测机制
        if (example1 == null) {
            //同步锁
            synchronized (SingletonExample5.class) {
                if (example1 == null) {
                    example1 = new SingletonExample5();
                }
            }
        }
        return example1;
    }
}
