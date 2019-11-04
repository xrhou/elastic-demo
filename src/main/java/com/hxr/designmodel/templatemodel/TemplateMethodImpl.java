package com.hxr.designmodel.templatemodel;

/**
 * @author houxiurong
 * @date 2019-09-14
 */
public class TemplateMethodImpl extends TemplateMethod {

    /**
     * 儿子不认可父亲的学习方法-考高分影响同学关系
     */
    @Override
    public void study() {
        System.out.println("....60分万岁...");
    }

    @Override
    public void love() {
        System.out.println("肤白貌美大长腿....");
    }


    public static void main(String[] args) {
        TemplateMethodImpl templateMethod = new TemplateMethodImpl();
        templateMethod.life();
    }
}
