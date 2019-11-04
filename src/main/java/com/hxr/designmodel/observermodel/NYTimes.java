package com.hxr.designmodel.observermodel;

/**
 * 纽约时报
 *
 * @author houxiurong
 * @date 2019-09-13
 */
public class NYTimes implements Observer {

    @Override
    public void notify(String twitter) {
        if (twitter != null && twitter.contains("money")) {
            System.out.println("NYTimes\nBig new in NY!\n" + twitter);
            System.out.println("------------------------------");
        }
    }
}
