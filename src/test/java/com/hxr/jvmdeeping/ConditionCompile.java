package com.hxr.jvmdeeping;

/**
 * Java 编译器 按条件编译优化
 *
 * @author houxiurong
 * @date 2019-09-20
 */
public class ConditionCompile {

    public static void main(String[] args) {
        /*if (true) {
            System.out.println("block 1");
        } else {
            System.out.println("block 2");
        }*/

       /* Map<String, String> map = new ConcurrentHashMap<>();
        map.computeIfAbsent("AaAa", key -> map.computeIfAbsent("BbBb", key2 -> "value"));
   */


        System.out.println(31 << 1);

    }
}
