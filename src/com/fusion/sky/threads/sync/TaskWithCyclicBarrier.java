package com.fusion.sky.threads.sync;

import java.util.concurrent.CyclicBarrier;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class TaskWithCyclicBarrier implements Runnable {
    private int id;
    private final CyclicBarrier cyclicBarrier;

    public TaskWithCyclicBarrier(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            System.out.println("Image Processor " + id + " is waiting");
            cyclicBarrier.await();
            System.out.println("Image Processor " + id + " is running");
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Image Processor " + id + " is released finally");
        }
    }
}
