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
package com.fusion.sky.threads.core;

import java.util.concurrent.*;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class FutureExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Future Example =======================================");
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(3000);
                return 42;
            }
        });
        System.out.println("Shutdown the Executor Service...   ");
        executorService.shutdown();

        // Do something else
        System.out.println("Do something else...");
        Thread.sleep(1000);

        System.out.println("Get the Result from Future ...");
        // Retrieve the result (blocking)
        Integer result = future.get();
        System.out.println("Result: " + result);
    }
}
