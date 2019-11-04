package com.hxr.jvmdeeping;

import java.util.ArrayList;
import java.util.List;

/**
 * String大小不断变长后导致内存溢出示例
 *
 * @author houxiurong
 * @date 2019-09-12
 */
public class StringOomMock {
    static String base = "string";

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
    }

    /**
     * -XX:MetaspaceSize=8M -XX:MaxMetaspaceSize=8M
     *
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     * 	at java.util.Arrays.copyOf(Arrays.java:3332)
     * 	at java.lang.AbstractStringBuilder.ensureCapacityInternal(AbstractStringBuilder.java:124)
     * 	at java.lang.AbstractStringBuilder.append(AbstractStringBuilder.java:448)
     * 	at java.lang.StringBuilder.append(StringBuilder.java:136)
     * 	at com.yibao.canaldemo.jvmdeeping.StringOomMock.main(StringOomMock.java:18)
     */
}