package com.java.app;

interface Cache<K,V> {
    public void put(K k, V v);
    public V get(K k);
    public void setCapacity(Integer cap);
    public void clear();
    public void printCache();
}
