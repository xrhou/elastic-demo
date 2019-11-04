package com.hxr.designmodel.adaptermodel;

/**
 * 零食小商贩
 * <p>
 * 抽象类实现接口的时候，
 * 对接口中的方法可以选择重写也可以选择不重写，不重写的方法由子类来完成。（目的降低接口开发的难度）
 *
 * @author houxiurong
 * @date 2019-09-14
 */
public abstract class AbstractFoodStore implements StudentRules {

    @Override
    public void familySign() {
        System.out.println("家长签字");
    }
}