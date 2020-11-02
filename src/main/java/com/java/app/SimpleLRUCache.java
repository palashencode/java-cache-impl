package com.java.app;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SimpleLRUCache<K, V> implements Cache<K, V> {

    Map<K,V> map;
    LinkedList<K> keyList;
    int capacity;

    SimpleLRUCache(int cap){
        this.capacity = cap;
        this.map = new HashMap<>();
        this.keyList = new LinkedList<>();
    }

    private boolean isFull(){
        return this.map.size() >= this.capacity;
    }

    @Override
    public void put(K k, V v) {
       
        V value = map.get(k);
        if(value != null ){
            keyList.remove(k);
            // keyList.addFirst(k);
            // map.put(k, v);
        }else{
            if(isFull()){
                K last = keyList.removeLast();
                map.remove(last);
                // keyList.addFirst(k);
                // map.put(k, v);
            }else{
                // Do Nothing
            }
        }   

        keyList.addFirst(k);
        map.put(k, v);
    }

    @Override
    public V get(K k) {
        V value = map.get(k);
        if(value != null){
            keyList.remove(k);
            keyList.addFirst(k);
        }

        return value;
    }

    @Override
    public void setCapacity(Integer cap) {
        this.capacity = cap;

    }

    @Override
    public void clear() {
        this.map = new HashMap<>();
        this.keyList = new LinkedList<>();

    }

    @Override
    public void printCache() {
        for(K key : this.keyList){
            System.out.println("key:"+key+"::Value:"+this.map.get(key));
        }

    }
    
}
