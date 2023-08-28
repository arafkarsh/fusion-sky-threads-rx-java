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
package com.fusion.sky.threads.pool;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class ThreadPoolWithThreadGroup {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Thread Pool with Thread Group =======================================");
        ThreadGroup threadGroup = new ThreadGroup("Fusion-Thread-Group");
        Runnable task = () -> {
            try {
                System.out.println("Executing task: " + Thread.currentThread().getName());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        System.out.println("Grouping the tasks.... ");
        Thread t1 = new Thread(threadGroup, task, "Thread-1");
        Thread t2 = new Thread(threadGroup, task, "Thread-2");
        Thread t3 = new Thread(threadGroup, task, "Thread-3");
        System.out.println("Start the tasks.... ");
        t1.start();
        t2.start();
        t3.start();
        System.out.println("Thread Group Name: " + threadGroup.getName());
        System.out.println("Thread Group Active Count: " + threadGroup.activeCount());

        // Wait for some time and then interrupt all threads in the group
        Thread.sleep(1000);
        threadGroup.interrupt();
        System.out.println("Interrupted all threads in ThreadGroup: " + threadGroup.getName());
    }
}
