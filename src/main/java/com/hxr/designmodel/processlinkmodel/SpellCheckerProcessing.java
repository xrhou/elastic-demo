package com.hxr.designmodel.processlinkmodel;

/**
 * SpellCheckerProcessing
 *
 * @author houxiurong
 * @date 2019-09-12
 */
public class SpellCheckerProcessing extends AbstractProcessingLinkModel<String> {

    @Override
    protected String handleWork(String text) {
        return text.replaceAll("labda", "lambda");
    }
}