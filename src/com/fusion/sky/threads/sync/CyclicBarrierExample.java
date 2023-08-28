package com.fusion.sky.threads.sync;

import java.util.concurrent.CyclicBarrier;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class CyclicBarrierExample {

    public static void main(String[] args) {
        System.out.println("Cyclic Barrier Example ========================================================");
        final int numberOfThreads = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(numberOfThreads, () -> {
            // This task will be executed once all thread reaches the barrier
            System.out.println("All Image Processor have arrived at the barrier, let's proceed");
        });

        // Create 3 threads
        for (int i = 0; i < numberOfThreads; i++) {
            /// Add a delay of 3 seconds between the start of each thread
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Start a Parallel Process (Thread)
            new Thread(new Task(i, cyclicBarrier)).start();
        }
    }

    static class Task implements Runnable {
        private int id;
        private CyclicBarrier cyclicBarrier;

        public Task(int id, CyclicBarrier cyclicBarrier) {
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
}