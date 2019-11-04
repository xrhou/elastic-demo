package com.hxr.jvmdeeping;

public class StackErrorMock {
    private static int index = 1;

    public void call(int a, int b) {
        int c = 5;
        long d = 4L;
        index++;
        call(a, b);
    }

    public static void main(String[] args) {
        StackErrorMock mock = new StackErrorMock();
        try {
            mock.call(1, 2);

        } catch (Throwable e) {
            System.out.println("Stack deep : " + index);
            e.printStackTrace();
        }
    }

    /*
    Stack deep : 18925
    java.lang.StackOverflowError

    */
}