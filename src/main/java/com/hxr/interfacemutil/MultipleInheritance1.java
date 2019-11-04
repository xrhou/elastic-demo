package com.hxr.interfacemutil;

/**
 * 接口多的继承
 *
 * @author houxiurong
 * @date 2019-09-27
 */
public class MultipleInheritance1 {

    interface A {
        default void say(int name) {
            System.out.println("A=" + name);
        }
    }

    interface B {
        default String say(short name) {
            System.out.println("B=" + name);
            return "hi, " + name;
        }
    }

    interface C extends A, B {
        default String say(String name) {
            System.out.println("C=" + name);
            return "hi, " + name;
        }
    }

    static class D implements C {

    }

    public static void main(String[] args) {
        D d = new D();
        byte a = 1;
        d.say(a);

        d.say("hoxiurong");
    }
}
