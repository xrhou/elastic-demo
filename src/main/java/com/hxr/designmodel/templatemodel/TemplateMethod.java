package com.hxr.designmodel.templatemodel;

/**
 * 模板要实现的方法
 *
 * @author houxiurong
 */
public abstract class TemplateMethod {

    /**
     * 模板方法用来控制子类的顺序 要想有人生必须按老爸的人生顺序来
     * 声明final不让子类覆盖这个方法，防止改变人生顺序
     */
    public final void life() {
        study();
        work();
        love();
    }

    /**
     * 家里穷更得用工学习
     */
    public void study() {
        System.out.println("每天晚上趴在邻居窗上学习....");
    }

    /**
     * 工作必须稳定
     */
    public void work() {
        System.out.println("work从一而终...");
    }

    /**
     * 恋爱自由让儿子自由恋去
     */
    public abstract void love();
}