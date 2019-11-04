package com.hxr.designmodel.factorymodel;

/**
 * 工厂方法
 *
 * @author houxiurong
 * @date 2019-09-13
 */
public interface BizDataProcess {

    /**
     * 获取BizData对应的 bizType
     *
     * @return topic
     */
    BizTypeEnum getBizType();

    /**
     * 不同bizData的处理
     *
     * @param bizData bizData
     */
    void process(BizData bizData);
}
