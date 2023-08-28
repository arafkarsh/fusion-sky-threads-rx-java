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

import java.util.concurrent.Exchanger;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class ExchangerExample {

    public static void main(String[] args) {
        System.out.println("Exchanger Example ========================================================");
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(new Task("I come from Thread 0", exchanger)).start();
        new Thread(new Task("I come from Thread 1", exchanger)).start();
    }

    static class Task implements Runnable {
        private String message;
        private final Exchanger<String> exchanger;
        public Task(String message, Exchanger<String> exchanger) {
            this.message = message;
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " before exchange: " + message);
                Thread.sleep(2000);
                // Exchange data and print the received message
                String receivedMessage = exchanger.exchange(message);
                System.out.println(Thread.currentThread().getName() + " received: " + receivedMessage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
