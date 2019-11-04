package com.hxr.executespooldeeping;

import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 素数
 *
 * @author houxiurong
 * @date 2019-10-24
 */
public class XXX {

    public static void main(String[] args) {
//        BigInteger bigInteger = BigInteger.ONE;
//        bigInteger.nextProbablePrime();

        List<String> list = new LinkedList<>();
        if (CollectionUtils.isEmpty(list)) {
            System.out.println("Collection is null");
        }
        //StringBuilder 线程不安全
        //StringBuffer 线程安全的,方法添加了synchronize关键字

        String[] split1 = "a.ab.abc".split("\\.");
        System.out.println(Arrays.toString(split1));

        String[] split2 = "a|ab|abc".split("\\|");
        System.out.println(Arrays.toString(split2));

        String[] split3 = "a|ab|abc".split("|");
        System.out.println(Arrays.toString(split3));

        BigDecimal bigDecimal1 = new BigDecimal(0.11D);
        System.out.println(bigDecimal1);
        BigDecimal bigDecimal12 = BigDecimal.valueOf(0.11D);
        System.out.println(bigDecimal12);
    }
}
