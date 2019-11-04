package com.hxr.designmodel.factorymodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 数据类型
 *
 * @author houxiurong
 * @date 2019-09-14
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BizData implements Serializable {

    private Integer id;

    /**
     * 类型
     */
    private String type;

    /**
     * data
     */
    private String data;


}
