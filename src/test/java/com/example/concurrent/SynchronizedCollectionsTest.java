package com.example.concurrent;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class SynchronizedCollectionsTest {
    @Test
    public void testSynchronizedCollection() throws InterruptedException {
        Collection<Integer> syncCollection = Collections.synchronizedCollection(new ArrayList<>());
        Runnable listOperations = () -> {
            syncCollection.addAll(Arrays.asList(1, 2, 3, 4, 5, 6));
        };

        Thread thread1 = new Thread(listOperations);
        Thread thread2 = new Thread(listOperations);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        Assert.assertEquals(12, syncCollection.size());
    }

    @Test
    public void testSynchronizedList() throws InterruptedException {
        List<String> syncCollection = Collections.synchronizedList(Arrays.asList("a", "b", "c"));
        List<String> uppercasedCollection = new ArrayList<>();

        Runnable listOperations = () -> {
            synchronized (syncCollection) {
                syncCollection.forEach((e) -> {
                    uppercasedCollection.add(e.toUpperCase());
                });
            }
        };

        Thread thread1 = new Thread(listOperations);
        Thread thread2 = new Thread(listOperations);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        Assert.assertEquals(6, uppercasedCollection.size());
    }

    @Test
    public void testSynchronizedSet() throws InterruptedException {
        Set<Integer> syncSet = Collections.synchronizedSet(new HashSet<>());
        SortedSet<Integer> syncSortedSet = Collections.synchronizedSortedSet(new TreeSet<>());

        Runnable setOperations = () -> {
            syncSet.addAll(Arrays.asList(1, 2, 3, 3, 5, 5));
        };

        Thread thread1 = new Thread(setOperations);
        Thread thread2 = new Thread(setOperations);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        Assert.assertEquals(4, syncSet.size());

        setOperations = () -> {
            syncSortedSet.addAll(Arrays.asList(5, 5, 4, 4, 1, 2));
        };

        thread1 = new Thread(setOperations);
        thread2 = new Thread(setOperations);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        Assert.assertEquals(4, syncSet.size());
    }

    static class MyEntry implements Map.Entry<Integer, String> {
        private Integer key;
        private String value;

        @Override
        public Integer getKey() {
            return key;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public String setValue(String value) {
            this.value = value;
            return value;
        }
    }

    @Test
    public void testSynchronizedMap() throws InterruptedException {
        Map<Integer, String> syncMap = Collections.synchronizedMap(new HashMap<>());
        Map<Integer, String> syncSortedMap = Collections.synchronizedSortedMap(new TreeMap<>());

        Runnable mapOperations = () -> {
            for (int i = 0; i < 10; i++) {
                syncMap.put(i, String.valueOf(i));
            }
        };

        Thread thread1 = new Thread(mapOperations);
        Thread thread2 = new Thread(mapOperations);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        Assert.assertEquals(10, syncMap.size());

        mapOperations = () -> {
            for (int i = 10; i > 0; i--) {
                syncSortedMap.put(i, String.valueOf(i));
            }
        };

        thread1 = new Thread(mapOperations);
        thread2 = new Thread(mapOperations);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        Assert.assertEquals(10, syncSortedMap.size());
    }
}
