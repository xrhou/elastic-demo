package com.hxr.jmmdeeping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 从ArrayList中移除元素
 * for i是从下标开始,不建议使用
 * 使用Iterate遍历方式是从元素开始遍历不会有漏掉的元素
 *
 * @author houxiurong
 * @date 2019-10-16
 */
public class ArrayListRemoveTest {

    public static void main0(String[] args) {
        List<Map<String, Object>> testList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> testMap = new HashMap<>();
            testMap.put("userName", "name" + i);
            testMap.put("userAge", i);
            if (i % 2 == 0) {
                testMap.put("userSex", "男");
            } else {
                testMap.put("userSex", "女");
            }
            testList.add(testMap);
        }
        for (Map<String, Object> map : testList) {
            System.out.println("foreach" + map);
        }
        System.out.println("-----------------------");

        for (Map<String, Object> map : testList) {
            if ((int) map.get("userAge") < 2) {
                testList.remove(map);
            }
        }

        for (Map<String, Object> map : testList) {
            System.out.println("foreach" + map + "--");
        }

        Iterator<Map<String, Object>> iterator = testList.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> map = iterator.next();
            if ((int) map.get("userAge") > 3) {
                iterator.remove();
            }
        }
        for (Map<String, Object> map : testList) {
            System.out.println("iterator" + map + "+++++");
        }

        /**
         * foreach{userSex=男, userName=name4, userAge=4}--
         * Exception in thread "main" java.lang.UnsupportedOperationException
         * 	at java.util.concurrent.CopyOnWriteArrayList$COWIterator.remove(CopyOnWriteArrayList.java:1176)
         * 	at com.hxr.jmmdeeping.ArrayListRemoveTest.main(ArrayListRemoveTest.java:52)
         */

    }

    public static void main(String[] args) {
        List<Map<String, Object>> testList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> testMap = new HashMap<>();
            testMap.put("userName", "name" + i);
            testMap.put("userAge", i);
            if (i % 2 == 0) {
                testMap.put("userSex", "男");
            } else {
                testMap.put("userSex", "女");
            }
            testList.add(testMap);
        }
        for (Map<String, Object> map : testList) {
            System.out.println("foreach" + map);
        }
        System.out.println("-------------------------------------------------");

        Iterator<Map<String, Object>> iterator = testList.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> map = iterator.next();
            if ((int) map.get("userAge") > 2) {
                iterator.remove();
            }
        }
        for (Map<String, Object> map : testList) {
            System.out.println("iterator" + map + "+++++");
        }
    }


}
