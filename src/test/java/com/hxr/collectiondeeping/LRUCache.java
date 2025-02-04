package com.hxr.collectiondeeping;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 最近最少使用Map
 *
 * @param <K>
 * @param <V>
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int CACHE_SIZE;

    public int getCACHE_SIZE() {
        return CACHE_SIZE;
    }

    /**
     * 传递进来最多能缓存多少数据
     *
     * @param cacheSize 缓存大小
     */
    public LRUCache(int cacheSize) {
        // true 表示让 LinkedHashMap 按照访问顺序来进行排序，最近访问的放在头部，最老访问的放在尾部。
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        CACHE_SIZE = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // 当 map中的数据量大于指定的缓存个数的时候，就自动删除最老的数据。
        return size() > CACHE_SIZE;
    }

    public static void main(String[] args) {
        Map map = new LRUCache(10);
        System.out.println("初始化时map大小:" + map.size());
        for (int i = 0; i < 40; i++) {
            map.put(i, "value" + i);
            System.out.print("当前map大小:" + map.size());
            System.out.println("," + map);
        }
    }
}