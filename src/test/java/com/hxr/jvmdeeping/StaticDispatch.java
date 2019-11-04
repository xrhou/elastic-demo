package com.hxr.jvmdeeping;

/**
 * @author houxiurong
 * @date 2019-09-14
 */
public class StaticDispatch {

    static abstract class Human {

    }

    static class Man extends Human {

    }

    static class Woman extends Human {

    }

    public void sayHello(Human human) {
        System.out.println("hello,guy!");
    }

    public void sayHello(Man man) {
        System.out.println("hello,gentleman!");
    }

    public void sayHello(Woman woman) {
        System.out.println("hello,lady!");
    }

    public static void main(String[] args) {
        StaticDispatch staticDispatch = new StaticDispatch();
        staticDispatch.sayHello(new Man());
        staticDispatch.sayHello(new Woman());
        //hello,gentleman!
        //hello,lady!

        Human man = new Man();
        Human woman = new Woman();
        staticDispatch.sayHello(man);
        staticDispatch.sayHello(woman);
        //hello,guy!
        //hello,guy!

        int[][][] array=new int[1][0][-1];
        System.out.println(array);
    }


}
