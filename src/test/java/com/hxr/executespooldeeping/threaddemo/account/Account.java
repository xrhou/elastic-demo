package com.hxr.executespooldeeping.threaddemo.account;

/**
 * 账户
 *
 * @author houxiurong
 * @date 2019-10-07
 */
public class Account {
    private int money = 600;

    //取钱
    public void getMoney(int num) {
        money = money - num;
    }

    //查看余额
    public int getYuE() {
        return money;
    }
}
