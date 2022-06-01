package com.example.concurrent;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

import static org.junit.Assert.*;

public class ConcurrentHashMapTest {
    @Test
    public void givenHashMap_whenSumParallel_thenError() throws Exception {
        Map<String, Integer> map = new HashMap<>();
        List<Integer> sumList = parallelSum100(map, 100);

        assertNotEquals(1, sumList
                .stream()
                .distinct()
                .count());
        long wrongResultCount = sumList
                .stream()
                .filter(num -> num != 100)
                .count();

        assertTrue(wrongResultCount > 0);
    }

    @Test
    public void givenConcurrentMap_whenSumParallel_thenCorrect()
            throws Exception {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        List<Integer> sumList = parallelSum100(map, 1000);

        assertEquals(1, sumList
                .stream()
                .distinct()
                .count());
        long wrongResultCount = sumList
                .stream()
                .filter(num -> num != 100)
                .count();

        assertEquals(0, wrongResultCount);
    }

    private List<Integer> parallelSum100(Map<String, Integer> map,
                                         int executionTimes) throws InterruptedException {
        List<Integer> sumList = new ArrayList<>(1000);
        for (int i = 0; i < executionTimes; i++) {
            map.put("test", 0);
            ExecutorService executorService =
                    Executors.newFixedThreadPool(4);
            for (int j = 0; j < 10; j++) {
                executorService.execute(() -> {
                    for (int k = 0; k < 10; k++)
                        map.computeIfPresent(
                                "test",
                                (key, value) -> value + 1
                        );
                });
            }
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            sumList.add(map.get("test"));
        }
        return sumList;
    }

    @Test(expected = NullPointerException.class)
    public void givenConcurrentHashMap_whenPutWithNullKey_thenThrowsNPE() {
        Map<String, Object> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put(null, new Object());
    }

    @Test(expected = NullPointerException.class)
    public void givenConcurrentHashMap_whenPutNullValue_thenThrowsNPE() {
        Map<String, Object> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("test", null);
    }

    @Test
    public void givenMaps_whenGetPut500KTimes_thenConcurrentMapFaster()
            throws Exception {
        Map<String, Object> hashtable = new Hashtable<>();
        Map<String, Object> synchronizedHashMap =
                Collections.synchronizedMap(new HashMap<>());
        Map<String, Object> concurrentHashMap = new ConcurrentHashMap<>();

        long hashtableAvgRuntime = timeElapseForGetPut(hashtable);
        long syncHashMapAvgRuntime =
                timeElapseForGetPut(synchronizedHashMap);
        long concurrentHashMapAvgRuntime =
                timeElapseForGetPut(concurrentHashMap);

        System.out.println("hashtableAvgRuntime: " + hashtableAvgRuntime);
        System.out.println("syncHashMapAvgRuntime: " + syncHashMapAvgRuntime);
        System.out.println("concurrentHashMapAvgRuntime: " + concurrentHashMapAvgRuntime);

        assertTrue(hashtableAvgRuntime > concurrentHashMapAvgRuntime);
        assertTrue(syncHashMapAvgRuntime > concurrentHashMapAvgRuntime);
    }

    private long timeElapseForGetPut(Map<String, Object> map)
            throws InterruptedException {
        ExecutorService executorService =
                Executors.newFixedThreadPool(4);
        long startTime = System.nanoTime();
        for (int i = 0; i < 4; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 500_000; j++) {
                    int value = ThreadLocalRandom
                            .current()
                            .nextInt(10000);
                    String key = String.valueOf(value);
                    map.put(key, value);
                    map.get(key);
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        return (System.nanoTime() - startTime) / 500_000;
    }

    private final static int MAX_SIZE = 100_000;

    @Test
    public void givenConcurrentMap_whenUpdatingAndGetSize_thenError()
            throws InterruptedException {
        List<Integer> mapSizes = new ArrayList<>();
        Map<String, Object> concurrentMap = new ConcurrentHashMap<>();
        ExecutorService executorService =
                Executors.newFixedThreadPool(8);
        Runnable collectMapSizes = () -> {
            for (int i = 0; i < MAX_SIZE; i++) {
                mapSizes.add(concurrentMap.size());
            }
        };
        Runnable updateMapData = () -> {
            for (int i = 0; i < MAX_SIZE; i++) {
                concurrentMap.put(String.valueOf(i), i);
            }
        };
        executorService.execute(updateMapData);
        executorService.execute(collectMapSizes);
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println(mapSizes.get(MAX_SIZE - 1).intValue());
        assertNotEquals(MAX_SIZE, mapSizes.get(MAX_SIZE - 1).intValue());
        assertEquals(MAX_SIZE, concurrentMap.size());
    }
}
