package com.java.app;

import java.util.LinkedHashMap;

public class LRUCache<K,V> implements Cache<K,V> {

    private int capacity;
    LinkedHashMap<K,CacheEntry> map = null;
    CacheEntry head;
    CacheEntry tail;



    LRUCache(int cap){
        this.capacity = cap;
        this.map = new LinkedHashMap<>();
    }

    @Override
    public void printCache(){
        System.out.println("Cap"+this.map.size());

        CacheEntry iter = head;
        while(iter != null){
            System.out.print(iter+",");
            iter = iter.next;
        }
    }

    private class CacheEntry{
        K key;
        V value;
        CacheEntry prev;
        CacheEntry next; 

        CacheEntry(K key,V value){
            this.value = value;
            this.key = key;
        }

        @Override
        public String toString(){
            return "["+key+":"+value+"]";
        }
    }

    private CacheEntry removeEntry(CacheEntry entry){
        
        if(entry.prev != null){
            entry.prev.next = entry.next;
        }else{
            this.head = entry.next;
        }

        if(entry.next != null){
            entry.next.prev = entry.prev;
        }else{
            this.tail = entry.prev;
        }

        return entry;
    }

    private void addNodeToHead(CacheEntry entry){
        
        if(this.head != null){
            this.head.prev = entry;
        }
        
        entry.next = this.head;
        entry.prev = null;

        this.head = entry;

        if(this.tail == null){
            this.tail = this.head;
        }    
        
    }

    private boolean isFull(){
       return  this.map.size() >= this.capacity;
    }

    @Override
    public void put(K k, V v) {
        
        if(map.get(k) != null){
            CacheEntry entry = map.get(k);
            removeEntry(entry);

            entry.value = v;
            addNodeToHead(entry);
            map.put(k, entry);
        }else{
            CacheEntry entry = new CacheEntry(k, v);
            entry.prev = entry.next = null;

            if(isFull()){
                //evict
                CacheEntry tail = map.get(this.tail.key);
                removeEntry(tail);
                map.remove(tail.key);
                addNodeToHead(entry);
                map.put(entry.key,entry);

            }else{
                addNodeToHead(entry);
                map.put(entry.key,entry);
            }

            //Adding to actual Cache
            // this.map.put(k, entry);
        }
    }

    @Override
    public V get(K k) {
       if(map.get(k) != null){

        CacheEntry entry = map.get(k);
        removeEntry(entry);
        addNodeToHead(entry);

        return entry.value;

       }else{
           return null;
       }
    }

    @Override
    public void setCapacity(Integer cap) {
       this.capacity = cap;

    }

    @Override
    public void clear() {
        this.map = new LinkedHashMap<>();
        this.head = null;
        this.tail = null;
    }
    
}
