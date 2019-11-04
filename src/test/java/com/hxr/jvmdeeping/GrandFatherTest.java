package com.hxr.jvmdeeping;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * 使用MethodHandle解决jvm 新加指令 invokeDynamic动态指令
 *
 * @author houxiurong
 * @date 2019-09-14
 */
public class GrandFatherTest {

    class GrandFather {
        void thinking() {
            System.out.println("i am grandFather");
        }
    }

    class Father extends GrandFather {
        void thinking() {
            System.out.println("i am father");
        }
    }

    class Son extends Father {
        void thinking() {
            try {
                MethodType methodType = MethodType.methodType(void.class);
                MethodHandle methodHandle = MethodHandles.lookup()
                        .findSpecial(GrandFather.class, "thinking", methodType, getClass());
                methodHandle.invoke(this);
            } catch (Throwable throwable) {
            }
        }
    }

    public static void main(String[] args) {
        (new GrandFatherTest().new Father()).thinking();
    }

}
