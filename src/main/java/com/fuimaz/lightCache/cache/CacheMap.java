package com.fuimaz.lightCache.cache;

/**
 * Created by juchen on 16/11/9.
 */
public interface CacheMap<K, V> {

    V put(K key, V value);

    V get(K key);

    V remove(K key);
}
