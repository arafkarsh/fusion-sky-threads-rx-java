/**
 * Copyright (c) 2023 Araf Karsh Hamid

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.

 * This program and the accompanying materials are licensed based on Apache 2 License.
 */
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