package com.hxr.designmodel.factorymodel;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 具体工厂处理实现
 *
 * @author houxiurong
 * @date 2019-09-13
 */
@Slf4j
@Service
public class BizDataProcessFactory {

    /**
     * 类型判断处理模式
     */
    private final Map<BizTypeEnum, BizDataProcess> processMap = Maps.newConcurrentMap();

    @Autowired
    public BizDataProcessFactory(List<BizDataProcess> bizDataProcessList) {
        bizDataProcessList.parallelStream().forEach(bizDataProcess -> processMap.put(bizDataProcess.getBizType(), bizDataProcess));
    }

    /**
     * TODO 事务管理 分布式锁
     */
    public void process(BizData bizData) {
        BizDataProcess process = processMap.get(BizTypeEnum.valueOfBizType(bizData.getType()));
        if (process == null) {
            log.error("找不到该biz的对应process id = " + bizData.getId());
            return;
        }
        try {
            process.process(bizData);
        } catch (Exception e) {
            //处理异常信息
        }
    }
}
