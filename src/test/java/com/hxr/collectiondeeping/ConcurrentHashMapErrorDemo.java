package com.hxr.collectiondeeping;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 并发map put的时候有问题实例
 *
 * @author houxiurong
 * @date 2019-09-30
 */
public class ConcurrentHashMapErrorDemo {

    static ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<>();

    public static void main0(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //thread-1
        executorService.submit(() -> {
            List<String> lis1 = new CopyOnWriteArrayList<>();
            lis1.add("device1");
            lis1.add("device2");

            map.put("topic1", lis1);
            System.out.println(JSON.toJSONString(map));
        });

        //thread-2
        executorService.submit(() -> {
            List<String> lis1 = new CopyOnWriteArrayList<>();
            lis1.add("device11");
            lis1.add("device22");

            map.put("topic1", lis1);
            System.out.println(JSON.toJSONString(map));
        });

        //thread-3
        executorService.submit(() -> {
            List<String> lis1 = new CopyOnWriteArrayList<>();
            lis1.add("device1111");
            lis1.add("device2222");

            map.put("topic2", lis1);
            System.out.println(JSON.toJSONString(map));
        });

        executorService.shutdown();

        /**
         * 数据丢失
         * {"topic1":["device11","device22"],"topic2":["device1111","device2222"]}
         * {"topic1":["device11","device22"],"topic2":["device1111","device2222"]}
         * {"topic1":["device11","device22"],"topic2":["device1111","device2222"]}
         */
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //thread-1
        executorService.submit(() -> {
            List<String> list1 = new CopyOnWriteArrayList<>();
            list1.add("device1");
            list1.add("device2");

            List<String> oldList = map.putIfAbsent("topic1", list1);
            if (oldList != null) {
                oldList.addAll(list1);
            }
            System.out.println(JSON.toJSONString(map));
        });

        //thread-2
        executorService.submit(() -> {
            List<String> list1 = new CopyOnWriteArrayList<>();
            list1.add("device11");
            list1.add("device22");

            List<String> oldList = map.putIfAbsent("topic1", list1);
            if (null != oldList) {
                oldList.addAll(list1);
            }
            System.out.println(JSON.toJSONString(map));
        });

        //thread-3
        executorService.submit(() -> {
            List<String> list1 = new CopyOnWriteArrayList<>();
            list1.add("device1111");
            list1.add("device2222");

            List<String> oldList = map.putIfAbsent("topic2", list1);
            if (null != oldList) {
                oldList.addAll(list1);
            }
            System.out.println(JSON.toJSONString(map));
        });

        executorService.shutdown();

        /**
         * {"topic1":["device1","device2","device11","device22"],"topic2":["device1111","device2222"]}
         * {"topic1":["device1","device2","device11","device22"],"topic2":["device1111","device2222"]}
         * {"topic1":["device1","device2","device11","device22"],"topic2":["device1111","device2222"]}
         */
    }


}
