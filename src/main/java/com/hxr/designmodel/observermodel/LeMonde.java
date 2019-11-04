package com.hxr.designmodel.observermodel;

/**
 * 世界卫报
 *
 * @author houxiurong
 * @date 2019-09-13
 */
public class LeMonde implements Observer {

    @Override
    public void notify(String twitter) {
        if (twitter != null && twitter.contains("wine")) {
            System.out.println("LeMonde\nToday cheese,wine and news !\n" + twitter);
            System.out.println("------------------------------");
        }
    }
}
