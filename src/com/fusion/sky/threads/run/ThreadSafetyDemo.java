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

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafetyDemo {

    private int numberOfThreads = 10;
    // Non-thread-safe variable
    private int nonSafeCounter = 0;

    // Thread-safe variable
    private AtomicInteger safeCounter = new AtomicInteger(0);


    /**
     * Create with 10 Threads
     */
    public ThreadSafetyDemo() {
        this(10);
    }

    /**
     * Creates a new instance of ThreadSafetyDemo with the number of Threads
     * @param _numberOfThreads
     */
    public ThreadSafetyDemo(int _numberOfThreads) {
        numberOfThreads = _numberOfThreads;
    }

    /**
     * Thread Not Safe Demo for Integer Arithmetic
     * @return
     */
    public int threadNotSafeCall() {
        Thread[] threads = new Thread[numberOfThreads];
        // Spawn 10 threads to increment nonSafeCounter
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    nonSafeCounter++;
                }
            });
            threads[i].start();
        }
        // Wait for all threads to finish
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("<< Non-thread-safe counter value: " + nonSafeCounter);
        return nonSafeCounter;
    }

    /**
     * Thread Safe Demo for Integer Arithmetic
     * @return
     */
    public AtomicInteger threadSafeCall() {
        Thread[] threads = new Thread[numberOfThreads];
        // Spawn 10 threads to increment safeCounter
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    safeCounter.incrementAndGet();
                }
            });
            threads[i].start();
        }
        // Wait for all threads to finish
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("<< CAS Thread-safe counter value: " + safeCounter.get());
        return safeCounter;
    }

    public void reset() {
        nonSafeCounter = 0;
        safeCounter.set(0);
    }

    /**
     * Test the Atomic Operations
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        ThreadSafetyDemo demo = new ThreadSafetyDemo(50);

        for(int x=1; x<6; x++) {
            System.out.println(x+" >> Testing Atomic Operations on Primitive variables ============================= >>");
            // Run non-thread-safe counter
            int ntsafe = demo.threadNotSafeCall();
            // Run thread-safe counter
            AtomicInteger tsafe = demo.threadSafeCall();
            if(ntsafe == tsafe.get()) {
                System.out.println(x+" >> You are lucky in this iterations! Luck doesn't favour you all the time !!! === >>");
            } else {
                System.out.println(x+" >> The difference in the counter shows integer operations are not thread safe === >>");
            }
            Thread.sleep(3000);
            demo.reset();
        }
    }
}

