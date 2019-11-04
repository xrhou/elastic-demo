package com.hxr.autoproxymybatisspring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * spring容器管理mybatis的接口
 *
 * @author houxiurong
 * @date 2019-10-18
 */
@ComponentScan("com.hxr.autoproxymybatisspring")
@Configuration
//@ImportResource("classpath:spring.xml")
@MyScanMapper
public class AppManage {

    /**
     * 这种方式如果IDao很多的话不适用-spring容器使用的是registerSingleton方式
     *
     * @return
     */
//    @Bean
//    public IDao iDao() {
//        //反射代理-注入到spring容器中
//        IDao iDao = (IDao) MySqlSession.queryMapper(IDao.class);
//        return iDao;
//    }
}
