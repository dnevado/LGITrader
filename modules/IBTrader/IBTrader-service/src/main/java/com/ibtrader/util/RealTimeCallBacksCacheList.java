package com.ibtrader.util;

import java.util.LinkedHashMap;
import java.util.Map;

/* CACHE DE SERIES DE COTIZACIONES PARA CALCULO DE INDICADORES */

public class RealTimeCallBacksCacheList <K, V> extends LinkedHashMap<K, V> {
	 
    private static int MAX_ENTRIES = 50;
 
    public RealTimeCallBacksCacheList(int initialCapacity, float loadFactor, boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
        MAX_ENTRIES = initialCapacity;
    }
 
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_ENTRIES;
    }
}