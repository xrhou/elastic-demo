package com.hxr.autoproxymybatisspring;

import com.hxr.autoproxymybatisspring.mapper.IDao;
import org.springframework.beans.factory.FactoryBean;

/**
 * FactoryBean-是一个bean工厂,本身是一个bean,FactoryBean会生产bean,获取bean
 *
 * @author houxiurong
 * @date 2019-10-18
 */
//@Component
public class MyFactoryBean implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        IDao iDao = (IDao) MySqlSession.queryMapper(IDao.class);
        return iDao;
    }

    @Override
    public Class<?> getObjectType() {
        return IDao.class;
    }
}
