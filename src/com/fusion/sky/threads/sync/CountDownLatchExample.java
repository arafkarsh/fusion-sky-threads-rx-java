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

import java.util.concurrent.CountDownLatch;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class CountDownLatchExample {

    private static final String sLine = "-------------------------------------------------------------------------------";

    public static void main(String[] args) throws InterruptedException {
        // Initialize the CountDownLatch with a count of 3
        CountDownLatch latch = new CountDownLatch(3);

        System.out.println(sLine+"\nStarting All Services.");
        // Start service initialization threads
        new ExternalServiceWithLatch("DatabaseService", 9000, latch).start();
        new ExternalServiceWithLatch("CacheService", 5000, latch).start();
        new ExternalServiceWithLatch("LoggingService", 3000, latch).start();

        // Wait for all services to initialize
        System.out.println("Waiting for All Services to complete initialization.\n"+sLine);
        latch.await();

        // Now, proceed with the main service logic
        System.out.println("All services are up, starting main application. \n"+sLine);
    }
}
