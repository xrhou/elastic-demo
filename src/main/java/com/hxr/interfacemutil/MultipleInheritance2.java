package com.hxr.interfacemutil;

/**
 * java 8 多重继承
 *
 * @author houxiurong
 * @date 2019-09-27
 */
public class MultipleInheritance2 {

    interface A {
        default void say(int a) {
            System.out.println("A");
        }

        default void run() {
            System.out.println("A.run");
        }
    }

    interface B extends A {
        default void say(int a) {
            System.out.println("B");
        }

        default void play() {
            System.out.println("B.play");
        }
    }

    interface C extends A, B {

    }

    static class D implements C {

    }

    public static void main(String[] args) {
        D d = new D();
        d.run();
        d.play();
    }
}
