package com.hxr.executespooldeeping;

import com.alibaba.fastjson.JSON;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 具有优先级的任务放入 优先阻塞队列
 *
 * @author houxiurong
 * @date 2019-09-16
 */
public class PriorityBlockingQueueTest {

    static class Task implements Comparable<Task> {

        /**
         * 优先级 默认0
         */
        private int priority = 0;
        /**
         * 任务名称
         */
        private String taskName;


        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public int compareTo(Task o) {
            if (this.priority >= o.getPriority()) {
                return 1;
            } else {
                return -1;
            }
        }

        public void doSomething() {
            System.out.println("我要做些事情:taskName=" + taskName + ",我的优先级是:priority=" + priority);
        }
    }


    public static void main(String[] args) {
        //创建任务,并添加到队列
        PriorityBlockingQueue<Task> priorityQueue = new PriorityBlockingQueue<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            task.setPriority(random.nextInt(10));
            task.setTaskName("taskName_" + i);
            priorityQueue.put(task);
        }

        //取出队列任务按照顺序开始执行
        //任务执行顺序和放入前后顺序没有关系,只和任务的优先级有关系
        while (!priorityQueue.isEmpty()) {
            Task task = priorityQueue.poll();
            if (null != task) {
                task.doSomething();
                System.out.print(","+JSON.toJSONString(priorityQueue.peek()));
            }
        }

    }
}
