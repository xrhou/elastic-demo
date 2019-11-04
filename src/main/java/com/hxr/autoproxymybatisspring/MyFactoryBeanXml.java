package com.hxr.autoproxymybatisspring;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;

/**
 * FactoryBean-是一个bean工厂,本身是一个bean,FactoryBean会生产bean,获取bean
 *
 * @author houxiurong
 * @date 2019-10-18
 */
@Service
public class MyFactoryBeanXml implements FactoryBean {

    Class classInterface;

    @Override
    public Object getObject() throws Exception {
        Object o = MySqlSession.queryMapper(classInterface);
        return o;
    }

    @Override
    public Class<?> getObjectType() {
        return classInterface;
    }

    public void setClassInterface(Class classInterface) {
        this.classInterface = classInterface;
    }

}
