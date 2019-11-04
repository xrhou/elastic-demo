package com.hxr.collectiondeeping;

import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 重新LinkedHashMap,如果元素超过最大容量，则删除最老的元素
 *
 * @author houxiurong
 * @date 2019-09-14
 */
public class LinkedHashMapTest {

    public static void main(String[] args) {
        Map map = new FixedSizeLinkedHashMap();
        System.out.println("初始化时map大小:" + map.size());
        for (int i = 0; i < 50; i++) {
            map.put(i, "value" + i);
            System.out.print("当前map大小:" + map.size());
            System.out.println("," + map);
            System.out.println("," + JSON.toJSONString(map));
        }
    }
}

/**
 * 固定大小的LinkedHashMap
 */
class FixedSizeLinkedHashMap extends LinkedHashMap {
    private static final long serialVersionUID = 6918023506928428613L;

    private static int MAX_ENTRIES = 10;

    /**
     * 如果Map的尺寸大于设定的最大长度，返回true，再新加入对象时删除最老的对象
     *
     * @param eldest 最老元素
     * @return true
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_ENTRIES;
    }
}