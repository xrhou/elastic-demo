package com.hxr.designmodel.factorymodel;

import lombok.Setter;

import java.util.Objects;

public enum BizTypeEnum {
    /**
     * biz type
     */
    LOAD("LOAD", "贷款"),
    TRANSFER("TRANSFER", "转账"),
    ;

    @Setter
    private String bizType;

    @Setter
    private String desc;

    BizTypeEnum(String bizType, String desc) {
        this.bizType = bizType;
        this.desc = desc;
    }

    public static BizTypeEnum valueOfBizType(String bizType) {
        for (BizTypeEnum bizTypeEnum : BizTypeEnum.values()) {
            if (Objects.equals(bizTypeEnum.bizType, bizType)) {
                return bizTypeEnum;
            }
        }
        return null;
    }
}
    