package com.hxr.queuedeeping;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 优先队列的任务场景使用案例
 *
 * @author houxiurong
 * @date 2019-10-20
 */
public class PriorityBlockingQueueTest {

    static class Task implements Comparable<Task> {

        private int priority = 0;
        private String taskName;

        @Override
        public int compareTo(Task task) {
            if (this.getPriority() > task.priority) {
                return 1;
            } else {
                return -1;
            }
        }

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

        public void doSomeThing() {
            System.out.println(taskName + "--" + priority);
        }
    }

    public static void main(String[] args) {
        //创建任务并添加到队列中
        PriorityBlockingQueue<Task> priorityQueue = new PriorityBlockingQueue<>();
        Random random = new Random();
        for (int i = 0; i < 10; ++i) {
            Task task = new Task();
            task.setPriority(random.nextInt(10));
            task.setTaskName("taskName" + i);
            priorityQueue.offer(task);
        }


        //取出任务待执行
        while (!priorityQueue.isEmpty()) {
            Task task = priorityQueue.poll();
            if (null != task) {
                task.doSomeThing();
            }
        }
    }


}
