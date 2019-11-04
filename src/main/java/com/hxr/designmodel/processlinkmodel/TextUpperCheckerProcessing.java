package com.hxr.designmodel.processlinkmodel;

/**
 * TextUpperCheckerProcessing
 *
 * @author houxiurong
 * @date 2019-09-12
 */
public class TextUpperCheckerProcessing extends AbstractProcessingLinkModel<String> {

    @Override
    protected String handleWork(String text) {
        return text.toUpperCase();
    }
}