package com.hxr.designmodel.proxymodel.staticproxymodel.girlproxy;

/**
 * @author houxiurong
 * @date 2019-10-06
 */
public class MailTest {

    public static void main(String[] args) {
        GirlOne girlOne = new GirlOne();
        ProxyClassZhuiImpl proxyClassZhui = new ProxyClassZhuiImpl(girlOne);
        proxyClassZhui.kouHong();
        proxyClassZhui.maiHua();

        System.out.println("------------------------");

        GirlTwo girlTwo = new GirlTwo();
        ProxyClassZhuiImpl proxyClassZhui2 = new ProxyClassZhuiImpl(girlTwo);
        proxyClassZhui2.kouHong();
        proxyClassZhui2.maiHua();
    }
}
