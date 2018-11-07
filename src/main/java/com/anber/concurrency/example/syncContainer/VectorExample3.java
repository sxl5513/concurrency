package com.anber.concurrency.example.syncContainer;


import java.util.Iterator;
import java.util.Vector;

/**
 * @author anber
 * @date 2018/11/7
 **/
public class VectorExample3 {

    /**
     * java.util.ConcurrentModificationException
     * @param v
     */
    private static void test1(Vector<Integer> v) {
        for (Integer i : v) {
            if (i == 3) {
                v.remove(i);
            }
        }
    }

    /**
     * java.util.ConcurrentModificationException
     * @param v
     */
    private static void test2(Vector<Integer> v) {
        Iterator<Integer> iterator = v.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next.equals(3)) {
                v.remove(next);
            }
        }
    }

    /**
     * success
     * @param v
     */
    private static void test3(Vector<Integer> v) {
        for (int i = 0; i < v.size(); i++) {
            if (v.get(i).equals(3)) {
                v.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        test3(vector);
    }


}
