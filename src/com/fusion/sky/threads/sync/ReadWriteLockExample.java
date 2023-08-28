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

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class ReadWriteLockExample {
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private int sharedResource = 0;
    public static void main(String[] args) {
        System.out.println("ReentrantReadWriteLock Example ========================================================");
        ReadWriteLockExample example = new ReadWriteLockExample();
        // Create multiple threads for reading and writing
        new Thread(() -> { example.writeResource((int) (Math.random() * 100)); }, "Writer-1").start();
        new Thread(() -> { example.readResource(); }, "Reader-1").start();
        new Thread(() -> { example.readResource(); }, "Reader-2").start();
        new Thread(() -> { example.readResource(); }, "Reader-3").start();

        new Thread(() -> { example.writeResource((int) (Math.random() * 100)); },"Writer-2").start();
        new Thread(() -> { example.readResource(); }, "Reader-4").start();
        new Thread(() -> { example.readResource(); }, "Reader-5").start();
    }

    public void readResource() {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " reads " + sharedResource);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void writeResource(int value) {
        readWriteLock.writeLock().lock();
        try {
            sharedResource = value;
            System.out.println(Thread.currentThread().getName() + " writes " + sharedResource);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}
