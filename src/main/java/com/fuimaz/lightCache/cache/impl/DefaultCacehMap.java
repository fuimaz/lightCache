package com.fuimaz.lightCache.cache.impl;

import com.fuimaz.lightCache.cache.CacheMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by juchen on 16/11/9.
 */
public class DefaultCacehMap<K, V> implements CacheMap<K, V> {
    private static Map cacheMap;

    public DefaultCacehMap() {
        cacheMap = new HashMap();
    }

    public DefaultCacehMap(int initialSize) {
        cacheMap = new HashMap(initialSize);
    }

    public DefaultCacehMap(int initialSize, float loadFactor) {
        cacheMap = new HashMap(initialSize, loadFactor);
    }

    @Override
    public V put(K key, V value) {
        return (V)cacheMap.put(key, value);
    }

    @Override
    public V get(K key) {
        return (V)cacheMap.get(key);
    }

    @Override
    public V remove(K key) {
        return (V)cacheMap.remove(key);
    }
}
