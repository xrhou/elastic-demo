package com.hxr.interfacemutil;

/**
 * 多层继承
 *
 * @author houxiurong
 * @date 2019-09-27
 */
public class MultipleInheritance3 {

    interface A1 {
        default void sayHello(int a) {
            System.out.println("A1");
        }
    }

    interface A2 extends A1 {

    }

    interface B {
        default void sayHello(int a) {
            System.out.println("B");
        }
    }

    interface C extends A2, B {
        default void sayHello(int a) {
            B.super.sayHello(a);
        }
    }
}
