package com.fusion.sky.threads.sync;

import java.util.concurrent.CountDownLatch;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class ExternalService extends Thread {
    private final String name;
    private final int initTime;
    private final CountDownLatch latch;

    public ExternalService(String name, int initTime, CountDownLatch latch) {
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
