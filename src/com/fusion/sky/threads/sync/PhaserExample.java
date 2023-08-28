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

import java.util.Random;
import java.util.concurrent.Phaser;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class PhaserExample {

    public static void main(String[] args) {
        System.out.println("Phaser Example ========================================================");

        Phaser phaser = new Phaser(3);  // Registers 3 parties (threads)
        new Thread(new Task(phaser)).start();   // Start the thread 1
        new Thread(new Task(phaser)).start();   // Start the thread 2
        new Thread(new Task(phaser)).start();   // Start the thread 3
    }

    static class Task implements Runnable {
        private final Phaser phaser;

        Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                // Stage 1
                Thread.sleep(getRandom());
                System.out.println("Thread ID = " + Thread.currentThread().getId() + " completed Stage 1");
                phaser.arriveAndAwaitAdvance(); // Current thread has arrived at this phase.

                // Stage 2
                Thread.sleep(getRandom());
                System.out.println("Thread ID = " + Thread.currentThread().getId() + " completed Stage 2");
                phaser.arriveAndAwaitAdvance();

                // Stage 3
                Thread.sleep(getRandom());
                System.out.println("Thread ID = " + Thread.currentThread().getId() + " completed Stage 3");
                phaser.arriveAndDeregister(); // De-registering the current thread as it has completed all Stages
                System.out.println("Thread ID = " + Thread.currentThread().getId() + " De-Registered");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public int getRandom() {
            Random rand = new Random();
            int randomNumber = rand.nextInt(2001) + 1000;
            System.out.println("Thread ID = " + Thread.currentThread().getId() + " Processing for " + randomNumber+" ms...");
            return randomNumber;
        }
    }
}
