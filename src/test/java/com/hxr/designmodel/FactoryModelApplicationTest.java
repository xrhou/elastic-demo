package com.hxr.designmodel;

import com.hxr.ElasticDemoApplication;
import com.hxr.designmodel.factorymodel.BizData;
import com.hxr.designmodel.factorymodel.BizDataProcessFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ElasticDemoApplication.class})
public class FactoryModelApplicationTest {

    @Autowired
    private BizDataProcessFactory bizDataProcessFactory;

    @Test
    public void bizDataProcessTest() {
        bizDataProcessFactory.process(BizData.builder().id(1).type("LOAD").data("我是贷款工厂要处理的逻辑").build());
    }

    @Test
    public void bizDataProcessTest2() {
        bizDataProcessFactory.process(BizData.builder().id(1).type("TRANSFER").data("我是转账工厂要处理的逻辑").build());
    }
}