package com.hxr.jvmdeeping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author houxiurong
 * @date 2019-09-11
 */
public class Demo {

    //both methods have same erasure
    //两种方法都有相同的擦除
   /* public static String method(List<String> list) {
        System.out.println("method-list导致泛型擦除");
        return "";
    }*/

    public static int method(List<Integer> list) {
        System.out.println("method导致泛型擦除");
        return 1;
    }

    public static void main(String[] args) {
        method(new ArrayList<Integer>());
        // method(new ArrayList<String>());

        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d);//true
        System.out.println(e == f);//false[-128,127]IntegerCache autoboxing
        System.out.println(c == (a + b)); //true
        System.out.println(c.equals(a + b));//true
        System.out.println(g == (a + b));//true
        System.out.println(g.equals(a + b));//false

        /*while (false){
            System.out.println("xxxxx");
        }*/

    }


   /* public static void main(String[] args) {
        List<String> list = Collections.unmodifiableList(Lists.newArrayList("a", "b"));
        list.add("c");
        System.out.println(list);

        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
    }*/
}

