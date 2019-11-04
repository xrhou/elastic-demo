package com.hxr.autoproxymybatisspring.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 接口
 *
 * @author houxiurong
 * @date 2019-10-18
 */
public interface IDao {

    /**
     * list
     *
     * @return
     */
    @Select("select * from t_order")
    List<Map<String, Object>> list();


}
