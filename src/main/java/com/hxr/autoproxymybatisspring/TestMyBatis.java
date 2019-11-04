package com.hxr.autoproxymybatisspring;

import com.hxr.autoproxymybatisspring.mapper.IDao;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * mybatis的实现原理
 *
 * @author houxiurong
 * @date 2019-10-18
 */
public class TestMyBatis {

    /**
     * 测试mybatis的动态代理-实例化接口
     *
     * @param args
     */
    public static void main0(String[] args) {
        IDao iDao = (IDao) MySqlSession.queryMapper(IDao.class);
        iDao.list();
    }

    public static void main1(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppManage.class);
        ac.getBean(IService.class).list();
    }

    public static void main2(String[] args) {
        //默认构造方法
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();

        ac.register(AppManage.class);

        ConfigurableListableBeanFactory beanFactory = ac.getBeanFactory();
        IDao iDao = (IDao) MySqlSession.queryMapper(IDao.class);
        beanFactory.registerSingleton("iDao", iDao);

        ac.refresh();

        //执行业务代码
        ac.getBean(IService.class).list();
    }

    /**
     * 工厂实现的FactoryBean处理
     */
    public static void main3(String[] args) {
        //默认构造方法
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(AppManage.class);
        ac.refresh();

        //执行业务代码
        System.out.println("name:" + ac.getBean("myFactoryBean"));
        System.out.println("object:" + ac.getBean("&myFactoryBean"));
        ac.getBean(IService.class).list();
    }

    /**
     * 工厂实现的可配置xml 方式 FactoryBean处理
     */
    public static void main(String[] args) {
        //默认构造方法
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(AppManage.class);
        ac.refresh();

        //执行业务代码
        ac.getBean(IService.class).list();
    }
}
