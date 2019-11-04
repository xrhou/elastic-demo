package com.hxr.designmodel.proxymodel.staticproxymodel.girlproxy;

/**
 * 静态代理类
 *
 * @author houxiurong
 * @date 2019-10-06
 */
public class ProxyClassZhuiImpl implements BeiRenZhuiInterface {

    BeiRenZhuiInterface beiRenZhuiInterface;

    public ProxyClassZhuiImpl(BeiRenZhuiInterface beiRenZhuiInterface) {
        this.beiRenZhuiInterface = beiRenZhuiInterface;
    }

    @Override
    public void maiHua() {
        beiRenZhuiInterface.maiHua();
    }

    @Override
    public void kouHong() {
        beiRenZhuiInterface.kouHong();
    }
}
