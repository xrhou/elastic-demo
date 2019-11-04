package com.hxr.annotationwarning;

/**
 * @author houxiurong
 * @date 2019-09-19
 */
public class BADLY_NAMED_CODE {

    enum colors {
        red, blue, gree;
    }

    static final int _FORTY_TWO = 42;

    public static int NOT_A_CONSTANT = _FORTY_TWO;

    protected void BADLY_NAMED_CODE() {
        return;
    }

    public void NOTcamelCASEmethodNAME() {
        return;
    }
}
