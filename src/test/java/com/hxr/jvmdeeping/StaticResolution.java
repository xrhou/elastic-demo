package com.hxr.jvmdeeping;

/**
 * 方法静态解析过程分析
 *
 * @author houxiurong
 * @date 2019-09-14
 */
public class StaticResolution {

    private static void sayHello() {
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        StaticResolution.sayHello();
    }


    /**
     * 字节码
     *   public static void main(java.lang.String[]);
     *     descriptor: ([Ljava/lang/String;)V
     *     flags: ACC_PUBLIC, ACC_STATIC
     *     Code:
     *       stack=0, locals=1, args_size=1
     *          0: invokestatic  #5                  // Method sayHello:()V
     *          3: return
     *       LineNumberTable:
     *         line 16: 0
     *         line 17: 3
     */
}
