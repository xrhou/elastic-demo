package com.hxr.threaddeeping;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * unsafe的使用：
 * unsafe具体实现是硬件级别的原子操作方法，而且方法都是native，JNI方式访问本地C++实现库。
 *
 * @author houxiurong
 * @date 2019-08-31
 */
public class TestUnsafe {

    //private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final Unsafe unsafe;

    private static final long stateOffset;

    private volatile long state = 2;

    static {
        try {
            //stateOffset = unsafe.objectFieldOffset(TestUnsafe.class.getDeclaredField("state"));

            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);

            //获取state在TestUnsafe中的偏移量
            stateOffset = unsafe.objectFieldOffset(TestUnsafe.class.getDeclaredField("state"));

        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            ex.printStackTrace();
            throw new Error(ex);
        }
    }

    public static void main(String[] args) {
        TestUnsafe testUnsafe = new TestUnsafe();
        Boolean success = unsafe.compareAndSwapLong(testUnsafe, stateOffset, 2, 3);
        System.out.println(success);
        System.out.println(testUnsafe.state);
    }

}
