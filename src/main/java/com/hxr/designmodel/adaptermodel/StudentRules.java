package com.hxr.designmodel.adaptermodel;

/**
 * 1.模式定义
 * 将某个类的接口转换成客户端期望的另一个接口表示。
 * 使得原本由于接口不兼容而不能一起工作的那些类能在一起工作。
 * <p>
 * 2.解决何种问题
 * 解决接口与接口实现类之间继承矛盾问题(在编程实现里面：当一个接口实现另一个接口的时候，
 * 有义务把接口中的方法都实现)
 * <p>
 * 3.模式特征
 * 使用抽象类分离了接口与接口实现。
 * 抽象类分摊接口中的方法。
 * 使得接口可以随意的选择接口中的方法来实现
 *
 * @author houxiurong
 * @date 2019-09-14
 */
public interface StudentRules {

    /**
     * 不受实现类欢迎的实现方法
     */
    void familySign();

    /**
     * 受实现类欢迎的实现方法
     */
    void filmPerWeek();
}
