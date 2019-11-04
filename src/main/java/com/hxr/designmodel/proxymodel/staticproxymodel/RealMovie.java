package com.hxr.designmodel.proxymodel.staticproxymodel;

/**
 * 真正的电影播放
 *
 * @author houxiurong
 * @date 2019-09-15
 */
public class RealMovie implements Movie {

    @Override
    public void play() {
        System.out.println("您正在观看电影《肖申克的救赎》");
    }

}