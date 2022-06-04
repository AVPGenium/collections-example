package com.example.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class Consumer implements Runnable {
    private final BlockingQueue queue;
    private AtomicLong counter = new AtomicLong();

    Consumer(BlockingQueue q) { queue = q; }

    public void run() {
        try {
            while (true) {
                consume(queue.take());
            }
        } catch (InterruptedException ex) {
            // handle
        }
    }
    void consume(Object x) {
        counter.incrementAndGet();
        System.out.println("Consumed element #" + counter.get());
    }
}
