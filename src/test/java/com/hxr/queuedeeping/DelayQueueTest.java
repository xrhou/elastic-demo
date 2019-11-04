package com.hxr.queuedeeping;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 并发无界延迟队列
 * 队列中的每个元素都有个过期时间,当从队列中获取元素时,只有快要过期的元素才会出队
 * 队头元素就是快要出队的元素
 * <p>
 * DelayQueue底层是Follow-Leader模式实现的
 * 其中private Thread leader = null;
 *
 * @author houxiurong
 * @date 2019-10-20
 */
public class DelayQueueTest {

    static class TaskDelay implements Delayed {

        private final long delayTime;//延迟时间
        private final long expire;//过期时间
        private String taskName;//任务名称

        /**
         * 剩余时间=到期时间-当期时间
         *
         * @param unit 时间
         * @return
         */
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expire - System.currentTimeMillis(),
                    TimeUnit.MILLISECONDS);
        }

        /**
         * 队列里面的有限规则
         *
         * @param o
         * @return
         */
        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        public TaskDelay(long delay, String taskName) {
            delayTime = delay;
            this.taskName = taskName;
            expire = System.currentTimeMillis() + delay;
        }

        @Override
        public String toString() {
            return "{TaskDelay{" +
                    "delayTime=" + delayTime +
                    ", expire=" + expire +
                    ", taskName='" + taskName + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //创建延迟队列
        DelayQueue<TaskDelay> delayQueue = new DelayQueue<>();

        //创建延迟任务
        Random random = new Random();
        for (int i = 0; i < 10; ++i) {
            TaskDelay taskDelay = new TaskDelay(random.nextInt(500), "task" + i);
            delayQueue.offer(taskDelay);
        }

        //依次取出并打印延迟任务
        TaskDelay taskDelay;
        try {
            //循环-避免虚假唤醒 则不能把全部元素都打印出来
            for (; ; ) {
                //获取过期任务并打印
                while ((taskDelay = delayQueue.take()) != null) {
                    System.out.println(taskDelay.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
