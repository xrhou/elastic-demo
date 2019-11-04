package com.hxr.executespooldeeping.threaddemo.account;

/**
 * 取钱线程
 *
 * @author houxiurong
 * @date 2019-10-07
 */
public class AccountThread implements Runnable {

    //volatile不能保证原子性
    Account account = new Account();

    @Override
    public void run() {
        synchronized (this) {
            if (account.getYuE() > 200) {
                System.out.println(Thread.currentThread().getName() + "来取钱,取了400元");
                account.getMoney(400);
            } else {
                System.out.println(Thread.currentThread().getName() + "来取钱,账户余额不足,失败,账户余额为" + account.getYuE());
            }
        }
    }
}
