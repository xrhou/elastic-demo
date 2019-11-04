package com.hxr.designmodel.proxymodel.staticproxymodel.girlproxy;

/**
 * @author houxiurong
 * @date 2019-10-06
 */
public class GirlOne implements BeiRenZhuiInterface {
    @Override
    public void maiHua() {
        System.out.println("我喜欢玫瑰-----");
    }

    @Override
    public void kouHong() {
        System.out.println("我喜欢迪欧----");
    }
}
