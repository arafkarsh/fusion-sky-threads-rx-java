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
        // Cyclic Barrier will wait for all the threads to reach this point
        while(true) {
            try {
                System.out.println("Image Processor " + id + " Loading Data...");
                Thread.sleep(2000);
                System.out.println("Image Processor " + id + " is waiting");
                cyclicBarrier.await();
                System.out.println("Image Processor " + id + " Processing Started...");
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("Image Processor " + id + " Processing Completed.");
            }
        }
    }
}
