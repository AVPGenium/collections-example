package com.example.concurrent;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

class Producer implements Runnable {
    private final BlockingQueue queue;
    private static AtomicLong counter = new AtomicLong();

    Producer(BlockingQueue q) { queue = q; }

    public void run() {
        try {
            while (true) {
                //Thread.sleep(100);
                queue.put(produce());
                counter.incrementAndGet();
                System.out.println("Produced element #" + counter.get());
            }
        } catch (InterruptedException ex) {
            // handle
        }
    }
    Object produce() {
        //return null;
        return new Random().nextInt();
    }
}
