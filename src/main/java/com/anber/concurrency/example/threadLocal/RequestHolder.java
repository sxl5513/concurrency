package com.anber.concurrency.example.threadLocal;

/**
 * @author anber
 * @date 2018/11/7
 **/
public class RequestHolder {

    private static final ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long getId(){
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }
}
