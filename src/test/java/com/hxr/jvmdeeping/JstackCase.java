package com.hxr.jvmdeeping;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author houxiurong
 * @date 2019-08-25
 */
public class JstackCase {

    private static Executor executor = Executors.newFixedThreadPool(5);
    private static Object lock = new Object();

    public static void main(String[] args) {
        Task task1 = new Task();
        Task task2 = new Task();
        executor.execute(task1);
        executor.execute(task2);
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                calculate();
            }
        }

        private void calculate() {
            int i = 0;
            while (true) {
                System.out.println(i);
                i++;
            }
        }

    }
}
