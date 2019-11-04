package com.hxr.threaddeeping;

import lombok.Getter;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * 倒计数-器运算:
 * 等人锁门案例
 * 秦国灭六国
 *
 * @author houxiurong
 * @date 2019-11-04
 */
public class CountDownLatchDemo {

    //倒计数
    private static CountDownLatch countDownLatch = new CountDownLatch(6);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 国被灭");
                countDownLatch.countDown();
            }, Objects.requireNonNull(CountryEnum.foreachCountryEnum(i)).getRetMessage())
                    .start();
        }
        //等待前面的任务执行完毕后才执行后面的任务
        countDownLatch.await();
        System.out.println("***************秦帝国-秦国一统天下");
    }

    /**
     * 等人锁门案例
     *
     * @throws InterruptedException
     */
    public static void closeDoor() throws InterruptedException {
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 上完自习，离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        //等待前面的任务执行完毕后才执行后面的任务
        countDownLatch.await();
        System.out.println("***************班长锁门走人");
    }
}

/**
 * 六国
 */
enum CountryEnum {
    ONE(1, "齐"),
    TWO(2, "楚"),
    THREE(3, "燕"),
    FOUR(4, "韩"),
    FIVE(5, "赵"),
    SIX(6, "魏"),
    ;

    @Getter
    private Integer retCode;
    @Getter
    private String retMessage;

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountryEnum foreachCountryEnum(int index) {
        CountryEnum[] countryEnums = CountryEnum.values();
        for (CountryEnum countryEnum : countryEnums) {
            if (index == countryEnum.getRetCode()) {
                return countryEnum;
            }
        }
        return null;
    }
}
