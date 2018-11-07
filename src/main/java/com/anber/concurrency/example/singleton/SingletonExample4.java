package com.anber.concurrency.example.singleton;


import com.anber.concurrency.annoations.NotRecommend;
import com.anber.concurrency.annoations.NotThreadSafe;
import com.anber.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式 -> 双重同步锁单例模式
 * 单例实例第一次使用时进行创建
 * @author anber
 * @date 2018/11/7
 **/
@NotThreadSafe
public class SingletonExample4 {

    /**
     * 私有构造
     */
    private SingletonExample4() {

    }

    /**
     * 单例
     */
    public static SingletonExample4 example1 = null;

    /**
     * 静态工厂方法
     *
     * example1 = new SingletonExample4();执行这步骤,在CPU中进行了三个步骤
     * 1.memory = allocate() 分配对象的内存空间
     * 2.ctorInstance() 初始化对象
     * 3.instance = memory 设置instance指向刚分配的内存
     *
     * 单线程是完全没有问题
     *
     * 在多线程环境中 可能出现的情况:
     * JVM和CPU优化中, 发生了指令重排
     * 1. memory = allocate() 分配对象的内存空间
     * 3. instance = memory 设置instance指向刚分配的内存
     * 2. ctorInstance() 初始化对象
     *
     * 当A线程进行3的步骤,但没有初始化, 此时B线程在判断是否为null, B会得到false的结果, 返回对象
     * 但是此时对象在A线程中还没有初始化
     *
     * @return
     */
    public static SingletonExample4 getInstance() {
        //双重检测机制
        if (example1 == null) { //B
            //同步锁
            synchronized (SingletonExample4.class) {
                if (example1 == null) {
                    example1 = new SingletonExample4(); // A-3
                }
            }
        }
        return example1;
    }
}
