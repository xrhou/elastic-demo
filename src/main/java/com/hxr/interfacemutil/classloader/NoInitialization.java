package com.hxr.interfacemutil.classloader;

/**
 * 非主动使用类字段
 *
 * @author houxiurong
 * @date 2019-09-28
 */
public class NoInitialization {

    /**
     * 输出不是 SubClass init 而是
     * ---------
     * SupperClass init
     * 123
     * --------
     *
     * @param args
     */
    public static void main(String[] args) {
        //子类引用父类中定义的静态字段,只会触发父类的初始化而不会触发子类的初始化
        //要使用子类的
        /*System.out.println(SubClass.value);

        SupperClass[] supperClasses = new SupperClass[10];

        System.out.println(ConstantClass.HELLO_WORLD);*/


        int i;
        int[] a = new int[5];
        for (i = 0; i <= 30; i++) {
            a[i] = 0;
            System.out.printf("%d:hello\n", i);
        }
        System.out.printf("%d:hello world", i);

    }


}
