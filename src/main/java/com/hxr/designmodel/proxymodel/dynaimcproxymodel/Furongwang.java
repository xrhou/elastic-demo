package com.hxr.designmodel.proxymodel.dynaimcproxymodel;

/**
 * @author houxiurong
 */
public class Furongwang implements SellCigarette {

    @Override
    public void sell(String name) {
        System.out.println("售卖的是正宗的芙蓉王,可以扫描条形码查证。" + name);
    }

}