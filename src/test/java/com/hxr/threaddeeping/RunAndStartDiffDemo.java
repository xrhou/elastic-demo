package com.hxr.threaddeeping;

/**
 * @author houxiurong
 * @date 2019-09-09
 */
public class RunAndStartDiffDemo {

    public static void main(String[] args) {
        Runnable runner = new Runnable();
        Thread thread = new Thread(runner, "新线程");

        System.out.println("thread.start():");
        thread.start();

        //区别run start
      /*  System.out.println("thread.run():");
        thread.run();*/

        System.out.println("runner.run():");
        runner.run();


    }
}

class Runnable implements java.lang.Runnable {

    private int food = 10;

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("线程名:" + Thread.currentThread().getName() + " food:" + (food--));
        }
    }
}
