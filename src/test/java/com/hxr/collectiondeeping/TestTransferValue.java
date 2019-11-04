package com.hxr.collectiondeeping;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 复习-内存 值传递和地址的引用
 *
 * @author houxiurong
 * @date 2019-11-04
 */
public class TestTransferValue {

    public void changeValue1(int age) {
        age = 30;
    }

    public void changeValue2(Person person) {
        person.setName("xxx");
    }

    public void changeValue3(String str) {
        str = "xxx";
    }

    public static void main(String[] args) {
        TestTransferValue testValue = new TestTransferValue();
        int age = 20;
        testValue.changeValue1(age);
        System.out.println("age---" + age);


        Person person = new Person("abc");
        testValue.changeValue2(person);
        System.out.println("personName----" + person.getName());


        String str = "abc";
        testValue.changeValue3(str);
        System.out.println("String---" + str);
    }

    /**
     * age---20
     * personName----xxx
     * String---abc
     */
}


@Getter
@Setter
@NoArgsConstructor
class Person {
    private int age;
    private String name;

    public String getName() {
        return name;
    }

    public Person(String name) {
        this.name = name;
    }
}
