package com.hxr.autoproxymybatisspring;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 注解实现
 *
 * @author houxiurong
 * @date 2019-10-18
 */
@Retention(RetentionPolicy.RUNTIME)
@Import(MyImportDefinitionBeanRegistrar.class)
public @interface MyScanMapper {

}
