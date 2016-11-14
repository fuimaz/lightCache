package com.fuimaz.lightCache.cacheControl.impl;

import com.fuimaz.lightCache.cache.CacheMap;
import com.fuimaz.lightCache.cacheControl.CacheControl;

import java.util.Stack;

import static com.fuimaz.lightCache.constants.DefaultKeyCount.DEFAULTKEYCOUNT;
import static com.fuimaz.lightCache.constants.DefaultKeyCount.DEFAULTREMOVECOUNTONCE;

/**
 * Created by juchen on 16/11/10.
 */
public class KeyCountControl<K, V> implements CacheControl {
    private int keyCount = 0;

    private int limitKeyCount = DEFAULTKEYCOUNT;

    private int removeCountOnce = DEFAULTREMOVECOUNTONCE;

    private CacheMap cacheMap;

    private Stack popStack = new Stack();

    private KeyCountControl() {

    }

    public KeyCountControl(CacheMap cacheMap) {
        this.cacheMap = cacheMap;
    }

    public KeyCountControl(CacheMap cacheMap, int limitKeyCount) {
        this.cacheMap = cacheMap;
        this.limitKeyCount = limitKeyCount;
    }

    public int getKeyCount(){
        return keyCount;
    }

    public int getLimitKeyCount() {
        return limitKeyCount;
    }

    public void setRemoveCountOnce(int removeCountOnce) {
        this.removeCountOnce = removeCountOnce;
    }

    public int getRemoveCountOnce() {
        return removeCountOnce;
    }

    public V put(K key, V value) {
        // push to stack
        popStack.push(key);

        if (++keyCount <= limitKeyCount) {
            return (V)cacheMap.put(key, value);
        }

        removeKeys();



        return null;
    }

    public V get(K key) {
        return (V)cacheMap.get(key);
    }

    private void removeKeys() {
        for (int i = 0; i < removeCountOnce; i++) {
            K popKey = (K)popStack.pop();
            cacheMap.remove(popKey);
         }
    }
}
