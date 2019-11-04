package com.hxr.executespooldeeping.threaddemo;

/**
 * 守护线程
 *
 * @author houxiurong
 * @date 2019-10-07
 */
public class DaemonThread extends Thread {

    public DaemonThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            System.out.println("DaemonThread-----" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            if (i == 6) {
                DaemonThread daemonThread = new DaemonThread("DaemonThread");
                daemonThread.setDaemon(true);//随main主线程存亡-守护线程
                daemonThread.start();
            }
            System.out.println("the main ---" + i);
        }
    }
}
