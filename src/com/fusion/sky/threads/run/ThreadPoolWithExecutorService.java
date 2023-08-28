package com.fusion.sky.threads.run;

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
