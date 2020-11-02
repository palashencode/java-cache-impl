package com.java.app;

import java.util.LinkedList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {   
        System.out.println( "Java Caching Algos" );
        System.out.println( "==================" );

        // Cache<Integer,String> cache = new LRUCache<>(3);
        // Cache<Integer,String> cache = new SimpleLRUCache<>(3);
        Cache<Integer,String> cache = new LFUCache<>(3);

        cache.put(1, "Palash");
        cache.put(2, "Prakash");
        cache.put(3, "Raju");
        // cache.put(4, "Rakesh");
        
        cache.get(1);
        cache.get(1);
        cache.get(1);
        cache.get(1);

        cache.get(2);
        cache.get(2);
        cache.get(2);
        cache.get(2);
        cache.get(2);
        cache.get(2);
        cache.get(3);
        cache.get(3);
        cache.get(3);
        cache.get(3);
        cache.get(3);
        cache.put(5, "Ramesh");
        // cache.get(3);
        // cache.get(3);
        // cache.put(1, "Palash");
        // cache.put(1, "PalashA");
        // cache.put(1, "PalashS");
        // cache.get(5);
        // cache.get(5);
        // cache.get(5);
        // cache.get(5);
        // cache.get(5);
        // cache.get(5);
        // cache.put(2, "Prakash");
        // cache.put(1, "Palash");
        // cache.put(2, "Prakash");
        // cache.put(3, "Raju");
        // cache.get(1);
        // cache.put(1, "Palash");

        cache.printCache();

        // LinkedList<Integer> list = new LinkedList<>();
        // list.add(1);
        // list.add(2);
        // list.add(3);
        // list.add(4);
        // list.add(5);
        // System.out.println(list);
        // list.remove(new Integer(3));
        // System.out.println(list);
        // list.addFirst(3);
        // System.out.println(list);
        // list.removeLast();
        // System.out.println(list);

    }
}
