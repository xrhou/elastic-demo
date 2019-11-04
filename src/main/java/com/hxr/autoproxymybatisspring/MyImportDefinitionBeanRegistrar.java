package com.hxr.autoproxymybatisspring;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 实现mapper接口登记
 *
 * @author houxiurong
 * @date 2019-10-18
 */
public class MyImportDefinitionBeanRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder definitionBuilder =
                BeanDefinitionBuilder.genericBeanDefinition(MyFactoryBeanXml.class);
        AbstractBeanDefinition beanDefinition = definitionBuilder.getBeanDefinition();
        beanDefinition.getPropertyValues()
                .add("classInterface", "com.hxr.autoproxymybatisspring.mapper.IDao");

        registry.registerBeanDefinition("iDao", beanDefinition);
    }
}
