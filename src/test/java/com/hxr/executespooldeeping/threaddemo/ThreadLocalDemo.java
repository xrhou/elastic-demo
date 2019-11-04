package com.hxr.executespooldeeping.threaddemo;

/**
 * 线程的本地变量存储
 * 创建三个线程，每个线程生成自己独立序列号
 *
 * @author houxiurong
 * @date 2019-10-09
 */
public class ThreadLocalDemo extends Thread {
    private ResultDate resultDate;

    public ThreadLocalDemo(ResultDate resultDate) {
        this.resultDate = resultDate;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + ",i=" + i + ",---num=" + resultDate.getNum());
        }
    }

    //生成自己独立的序号
    public static void main(String[] args) throws InterruptedException {
        ResultDate resultDate = new ResultDate();
        ThreadLocalDemo threadLocalDemo1 = new ThreadLocalDemo(resultDate);
        ThreadLocalDemo threadLocalDemo2 = new ThreadLocalDemo(resultDate);
        ThreadLocalDemo threadLocalDemo3 = new ThreadLocalDemo(resultDate);

        threadLocalDemo1.start();
        threadLocalDemo2.start();
        threadLocalDemo3.start();

        Thread.sleep(300);
        System.out.println(ResultDate.count);
    }


}

class ResultDate {
    // 生成序列号共享变量
    public static Integer count = 0;

    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public Integer getNum() {
        count = threadLocal.get() + 1;
        threadLocal.set(count);
        return count;
    }
}
