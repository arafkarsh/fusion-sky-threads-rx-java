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
public class ExternalServiceWithLatch extends Thread {
    private final String name;
    private final int initTime;
    private final CountDownLatch latch;

    public ExternalServiceWithLatch(String name, int initTime, CountDownLatch latch) {
        this.name = name;
        this.initTime = initTime;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            // Simulate service initialization time
            Thread.sleep(initTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Decrease the latch count
        latch.countDown();

        System.out.println(name + " Service is up!");
    }
}
