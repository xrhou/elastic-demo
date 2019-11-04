package com.hxr.designmodel.adaptermodel;

/**
 * 小学生
 *
 * @author houxiurong
 * @date 2019-09-14
 */
public class LitterStudent extends AbstractFoodStore {
    @Override
    public void filmPerWeek() {
        System.out.println("去看电影");
    }


    public static void main(String[] args) {
        StudentRules target = new LitterStudent();
        target.familySign();
        target.filmPerWeek();
    }
}