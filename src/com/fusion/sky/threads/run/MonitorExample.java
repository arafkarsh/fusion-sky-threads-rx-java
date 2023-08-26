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
public class MonitorExample {

    private boolean conditionMet = false;

    public synchronized void waitForCondition() throws InterruptedException {
        while (!conditionMet) {
            wait();  // Thread goes to waiting state
        }
        System.out.println(Thread.currentThread().getName() + " is executing.");
    }

    public synchronized void changeCondition() {
        conditionMet = true;
        // notify();    // Uncomment to wake up only one waiting thread
        notifyAll();   // Uncomment to wake up all waiting threads
    }

    public static void main(String[] args) {
        MonitorExample monitorExample = new MonitorExample();

        // Create multiple threads that wait for a condition
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " is Waiting.");
                    monitorExample.waitForCondition();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        // Change the condition and notify waiting threads after some delay
        new Thread(() -> {
            for(int x=1; x<6; x++) {
                try {
                    System.out.println(x+" >> "+Thread.currentThread().getName() + " is Sleeping.");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(x+" >> "+Thread.currentThread().getName() + " Change Condition.");
                monitorExample.changeCondition();
            }
        }).start();
    }
}
