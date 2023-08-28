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

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class ReentrantLockWithConditionExample {
    private final LinkedList<Integer> list = new LinkedList<>();
    private final int capacity = 7;
    private final int totalItems;
    private final Lock lock;
    private final Condition notEmpty;
    private final Condition notFull;

    /**
     * Create the Example with Total Items to be produced
     * to test the producer and consumer threads using the
     * conditions.
     *
     * @param _totalItems
     */
    public ReentrantLockWithConditionExample(int _totalItems) {
    	totalItems = _totalItems;
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    /**
     * Produces the items and adds to the queue if the queue is not full.
     * If the Queue is Full then the producer thread waits until the queue
     * is not full.
     * @throws InterruptedException
     */
    public void produce() throws InterruptedException {
        int value = 1;
        while (value <= totalItems) {
            // Lock is acquired
            lock.lock();
            try {
                // Wait until the queue is not full
                while (list.size() == capacity) {
                    // Wait for the queue to be not full and release the lock and wait.
                    notFull.await();
                }
                System.out.println(">> Producing >> " + value);
                list.add(value++);
                // Signal the consumer that the queue is not empty now
                notEmpty.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * Consumes the items from the queue if the queue is not empty.
     * If the Queue is Empty then the consumer thread waits until the queue
     * is not empty.
     * @throws InterruptedException
     */
    public void consume() throws InterruptedException {
        while (true) {
            // Lock is acquired
            lock.lock();
            try {
                // Wait until the queue is not empty
                while (list.size() == 0) {
                    // Wait for the queue to be not empty and release the lock and wait.
                    notEmpty.await();
                }
                int value = list.removeFirst();
                System.out.println("<< Consuming << " + value);
                // Signal the producer that the queue is not full now
                notFull.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * Test the Example
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Reentrant Lock with Condition Example ===============");
        ReentrantLockWithConditionExample demo = new ReentrantLockWithConditionExample(10);

        System.out.println("PRODUCER THREAD: Total Items : "+ demo.getTotalItems());
        // Producer Thread
        Thread producerThread = new Thread(() -> {
            try {
                demo.produce();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        System.out.println("CONSUMER THREAD: ");
        // Consumer Thread
        Thread consumerThread = new Thread(() -> {
            try {
                demo.consume();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producerThread.start();
        consumerThread.start();
    }

    public LinkedList<Integer> getList() {
        return list;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public Lock getLock() {
        return lock;
    }

    public Condition getNotEmpty() {
        return notEmpty;
    }

    public Condition getNotFull() {
        return notFull;
    }
}
