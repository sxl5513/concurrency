package com.anber.concurrency.example.syncContainer;

import com.anber.concurrency.annoations.NotThreadSafe;

import java.util.Vector;

/**
 * @author anber
 * @date 2018/11/7
 **/
@NotThreadSafe
public class VectorExample2 {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {

        while (true) {

            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread thread1 = new Thread(){
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread(){
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.get(i);
                    }
                }
            };
            thread1.run();
            thread2.run();
        }
    }


}
