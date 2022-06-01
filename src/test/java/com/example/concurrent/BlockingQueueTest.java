package com.example.concurrent;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
    @Test
    public void testBlockOnMaxSize() throws InterruptedException {
        BlockingQueue q = new ArrayBlockingQueue(100);
        Producer p1 = new Producer(q);
        Producer p2 = new Producer(q);
        //Consumer c1 = new Consumer(q);
        //Consumer c2 = new Consumer(q);

        Thread thread1 = new Thread(p1);
        Thread thread2 = new Thread(p2);
        thread1.start();
        thread2.start();
        Thread.sleep(100);

        Assert.assertEquals(100, q.size());
    }
}
