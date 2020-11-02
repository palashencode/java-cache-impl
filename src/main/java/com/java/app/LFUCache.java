package com.java.app;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class LFUCache<K,V> implements Cache<K,V>{

    private int capacity;
    private int min = -1 ;

    private Map<K,V> cacheMap;
    private Map<K,Integer> countMap;
    private Map<Integer,LinkedList<K>> listPerCount;

    LFUCache(int cap){
        this.capacity = cap;
        this.cacheMap = new HashMap<>();
        this.countMap = new HashMap<>();
        this.listPerCount = new HashMap<>();
        this.listPerCount.put(1, new LinkedList<>());
    }

    private boolean isFull(){
        return this.cacheMap.size() >= this.capacity;
    }

    @Override
    public void put(K k, V v) {
        // NEW ELEMENT
        if(cacheMap.get(k) == null){

        // EVICT IF MAP FULL
        if(isFull()){
            // if(listPerCount.get(min).size() != 0){
                K first = listPerCount.get(min).removeFirst();
                cacheMap.remove(first);
                countMap.remove(first);
            // }

            // Find the new minimum
            // if(listPerCount.get(min).size() == 0){
            //     int localMin = listPerCount.keySet().iterator().next();
            //     for(Integer localCount : listPerCount.keySet()){
            //         if(localCount <= localMin && listPerCount.get(localCount).size() > 0){
            //             localMin = localCount;
            //         }
            //     } 
            //     min = localMin;
            // }
        }

            cacheMap.put(k, v);
            countMap.put(k, 1); 
            listPerCount.get(1).add(k);
            min = 1;

        }else{
            cacheMap.put(k, v);
            get(k);
            // int count = countMap.get(k);
            // countMap.put(k, count + 1);
            // listPerCount.get(count).remove(k);
            // if(listPerCount.get(count+1) == null){
            //     listPerCount.put(count+1, new LinkedList<>());
            // }
            // listPerCount.get(count+1).add(k);
            // if(count == min && listPerCount.get(min).size() == 0 ){
            //     min++;
            // }
        }

    }

    @Override
    public V get(K k) {
        
        V value = cacheMap.get(k);
        if(value != null){
            int count = countMap.get(k);
            countMap.put(k, count + 1);
            listPerCount.get(count).remove(k);
            if(listPerCount.get(count + 1) == null){
                listPerCount.put(count + 1, new LinkedList<>());
            }
            listPerCount.get(count + 1).add(k);
            if(count == min && listPerCount.get(count).size() == 0){
                min++;
            }
        }

        return value;
    }

    @Override
    public void setCapacity(Integer cap) {
        this.capacity = cap;
    }

    @Override
    public void clear() {
        this.cacheMap = new HashMap<>();
        this.countMap = new HashMap<>();
        this.listPerCount = new HashMap<>();
        this.listPerCount.put(1, new LinkedList<>());
    }

    @Override
    public void printCache() {
        
        Set<Integer> countSet = new TreeSet<>(listPerCount.keySet());
        System.out.println("Min:"+min);
        List<K> localList = null;
        for(Integer count : countSet){
            localList = listPerCount.get(count);
            for(K key : localList){
                System.out.println("Count:"+count+":: Key:"+key+":: Value:"+cacheMap.get(key));
            }
        }

    }
    
}
