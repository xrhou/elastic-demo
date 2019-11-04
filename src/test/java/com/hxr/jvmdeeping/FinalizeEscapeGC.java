package com.hxr.jvmdeeping;

import lombok.Getter;

/**
 * 一次对象的自我拯救 只执行一次
 *
 * @author houxiurong
 * @date 2019-09-10
 */
public class FinalizeEscapeGC {

    @Getter
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes,我还活着的对象");
    }

    //对象自救设置为 this 引用
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();

        //对象第一次自救
        SAVE_HOOK = null;
        System.gc();
        //暂停0.5秒
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no,我这个对象的死了!");
        }

        //对象第一次自救
        SAVE_HOOK = null;
        System.gc();
        //暂停0.5秒
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no,我这个对象的死了!:(");
        }

        System.out.println("Math.round(2.5)="+Math.round(2.5));
        System.out.println("Math.round(-2.6)="+Math.round(-2.6));

    }
}
