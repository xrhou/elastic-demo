package com.hxr.collectiondeeping;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合类不安全问题
 * Exception in thread "22" java.util.ConcurrentModificationException
 *
 * @author houxiurong
 * @date 2019-11-04
 */
public class ContainerNotSafeDemo {

    public static void main0(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();
        //Collections.synchronizedList(new ArrayList<>());
        //new Vector<>();
        //new ArrayList<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().replace("-", "").substring(0, 4));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

        /**
         * Exception in thread "20" java.util.ConcurrentModificationException
         * 	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:901)
         * 	at java.util.ArrayList$Itr.next(ArrayList.java:851)
         * 	at java.util.AbstractCollection.toString(AbstractCollection.java:461)
         * 	at java.lang.String.valueOf(String.java:2994)
         * 	at java.io.PrintStream.println(PrintStream.java:821)
         * 	at com.hxr.collectiondeeping.ContainerNotSafeDemo.lambda$main$0(ContainerNotSafeDemo.java:20)
         * 	at java.lang.Thread.run(Thread.java:748)
         * Exception in thread "22" java.util.ConcurrentModificationException[null,
         * 794c1ce5f6f947e1811c7700f666645e,f8d854cf5cd047c9ab02aa7d4847b89b]
         */
        /**
         * 1.故障产生的现象
         * Exception in thread "20" java.util.ConcurrentModificationException
         *
         * 2.故障产生原因
         *
         * 3.解决方案
         * a.new Vector()
         * b.Collections.synchronizedList(new ArrayList<>())
         * c.
         *
         * 4.代码优化建议(错误问题不再第二次再犯)
         *
         */
    }

    public static void main(String[] args) {
        Set<String> set = new CopyOnWriteArraySet<>();
        //Collections.synchronizedSet(new ArrHashSet<>());
        //new HashSet<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().replace("-", "").substring(0, 4));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
