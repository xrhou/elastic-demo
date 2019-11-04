package com.hxr.designmodel.factorymodel.impl;

import com.alibaba.fastjson.JSON;
import com.hxr.designmodel.factorymodel.BizData;
import com.hxr.designmodel.factorymodel.BizDataProcess;
import com.hxr.designmodel.factorymodel.BizTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Load biz handle
 *
 * @author houxiurong
 * @date 2019-09-14
 */
@Slf4j
@Service
public class LoadProcessImpl implements BizDataProcess {

    @Override
    public BizTypeEnum getBizType() {
        return BizTypeEnum.LOAD;
    }

    @Override
    public void process(BizData bizData) {
        log.info("LoadProcess handle:" + JSON.toJSONString(bizData));
    }
}
