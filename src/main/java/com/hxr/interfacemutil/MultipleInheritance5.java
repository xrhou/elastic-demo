package com.hxr.interfacemutil;

/**
 * 1.类优先于接口。 如果一个子类继承的父类和接口有相同的方法实现。 那么子类继承父类的方法。
 * 2.子类型中的方法优先于父类型中的方法。
 * 3.如果以上条件都不满足，则必须显示覆盖override 或实现implements其方法，或者声明成abstract。
 *
 * @author houxiurong
 * @date 2019-09-28
 */
public class MultipleInheritance5 {
    interface A {
        default void say() {
            System.out.println("hello,A");
        }
    }

    static class B {
        public void say() {
            System.out.println("hello,B");
        }
    }

    static class C extends B implements A {

    }

    public static void main(String[] args) {
        C c = new C();
        c.say(); //hello,B
    }
}
