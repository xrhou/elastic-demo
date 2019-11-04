package com.hxr.lambdastream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 匿名内部类-变量的范围
 *
 * @author houxiurong
 * @date 2019-10-03
 */
public class MeaningOfThis {

    public final int value = 4;

    public void doIt() {
        int value = 6;
        Runnable runnable = new Runnable() {
            public final int value = 5;

            @Override
            public void run() {
                int value = 10;
                System.out.println(this.value);
            }
        };
        runnable.run();
    }

    public static void main(String[] args) {
        MeaningOfThis meaningOfThis = new MeaningOfThis();
        meaningOfThis.doIt();

        List<Integer> list = Arrays.asList(1, 2, 4, 5, 6, 7, 8, 9);
        System.out.println(list.stream().filter(m -> m % 2 == 0).collect(Collectors.toList()));
        System.out.println(list.stream().filter(m -> m % 2 != 0).collect(Collectors.toList()));

    }

    interface Predicate<T> {
        boolean test(T t);
    }
}
