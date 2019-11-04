package com.hxr.interfacemutil;

/**
 * 接口A2继承A1， 接口C继承A2和A1
 *
 * @author houxiurong
 * @date 2019-09-28
 */
public class MultipleInheritance4 {

    interface A1 {
        default void say() {
            System.out.println("A1");
        }
    }

    interface A2 extends A1 {

        @Override
        default void say() {
            System.out.println("A2");
        }
    }

    interface C extends A2, A1 {

    }

    static class D implements C {

    }

    public static void main(String[] args) {
        D d = new D();
        d.say();
    }
}
