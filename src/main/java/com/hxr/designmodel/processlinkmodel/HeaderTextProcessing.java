package com.hxr.designmodel.processlinkmodel;

/**
 * 责任链实现
 *
 * @author houxiurong
 * @date 2019-09-12
 */
public class HeaderTextProcessing extends AbstractProcessingLinkModel<String> {
    @Override
    protected String handleWork(String text) {
        return "From zou, Mark: " + text;
    }

    public static void main(String[] args) {
        AbstractProcessingLinkModel<String> p1 = new HeaderTextProcessing();
        AbstractProcessingLinkModel<String> p2 = new SpellCheckerProcessing();
        AbstractProcessingLinkModel<String> p3 = new TextUpperCheckerProcessing();

        p1.setSuccessor(p2);
        p2.setSuccessor(p3);

        String result1 = p1.handle("Are't labdas really sexy?!!");

        String result2 = result1 + p3.handle("houxiurong");
        System.out.println(result2);
    }
}



