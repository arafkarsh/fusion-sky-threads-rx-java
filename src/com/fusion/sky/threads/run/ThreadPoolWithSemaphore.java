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
package com.fusion.sky.threads.run;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class ThreadPoolWithSemaphore {

    private static final String dLine = "====================================================";
    private static final String sLine = "-------------------------------------------------------------------------------";

    /**
     * Main Method
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(sLine+"\nThread Pool with Semaphore "+dLine);
        System.out.println("Create a ThreadPool with 10 threads");
        // Create a fixed-size thread pool with 10 threads
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        System.out.println("Create a Semaphore with 5 permits");
        // Initialize a semaphore with 5 permits (concurrent tasks)
        Semaphore semaphore = new Semaphore(5);

        System.out.println("Create 20 Tasks for execution \n" + sLine);
        // Create 20 tasks to submit to the thread pool
        for (int i = 0; i < 20; i++) {
            threadPool.submit(new Task(semaphore));
        }
        System.out.println("20 Tasks created and submitted to the thread pool for execution \n" + sLine);
        System.out.println("Shutdown All the threads");
        // Shutdown the thread pool
        threadPool.shutdown();
        System.out.println(sLine);
    }

    static class Task implements Runnable {
        private final Semaphore semaphore;

        public Task(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                // Acquire a permit from the semaphore
                semaphore.acquire();

                // Simulate some work
                System.out.println("Task executed by " + Thread.currentThread().getName());
                Thread.sleep(2000);  // Sleep for 2 seconds to simulate some work

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                // Release the permit
                semaphore.release();
                System.out.println("Task completed by " + Thread.currentThread().getName());
            }
        }
    }
}
