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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class ThreadPoolWithExecutorService {

    public static void main(String[] args) {
        System.out.println("Thread Pool with Executor Service =======================================");
        ExecutorService executor = Executors.newFixedThreadPool(3); // 3 threads in pool

        Runnable task = () -> {
            try {
                System.out.println("Executing task: " + Thread.currentThread().getName());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        System.out.println("Executing tasks in a thread pool of 3 threads.... ");
        executor.execute(task);
        executor.execute(task);
        executor.execute(task);

        try {
            System.out.println("Main Thread Sleeping for 5 seconds... ");
            Thread.sleep(5000);
            System.out.println("Main Thread Waking up....  ");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Shutting down the thread pool... ");
        executor.shutdown();
    }
}
