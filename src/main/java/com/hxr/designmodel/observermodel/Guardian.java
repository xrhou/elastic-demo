package com.hxr.designmodel.observermodel;

/**
 * 英国卫报
 *
 * @author houxiurong
 * @date 2019-09-13
 */
public class Guardian implements Observer {

    @Override
    public void notify(String twitter) {
        if (twitter != null && twitter.contains("queen")) {
            System.out.println("Guardian\nYet another news in London...\n" + twitter);
            System.out.println("------------------------------");
        }
    }
}
