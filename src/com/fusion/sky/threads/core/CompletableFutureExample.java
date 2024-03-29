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

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
public class CompletableFutureExample {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("CompletableFuture Example =======================================");

        nonBlocking();

        System.out.println("System Waiting for the Completable Future to Complete....");
        Thread.sleep(10000);
        System.out.println("System Processing Complete....");

        // blocking();
    }

    public static void nonBlocking() {
        CompletableFuture<Void> fetchUserAndOrders = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Fetching User ID... ");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int userId = 24;
            System.out.println("Returning User ID = "+userId);
            return userId; // User ID
        }).exceptionally(ex -> {
            System.out.println("Exception while fetching User ID: " + ex.getMessage());
            return null;
        }).thenApplyAsync(userId -> {
            if (userId == null) throw new IllegalStateException("User ID can't be null");
            String userName = "User_" + userId;
            System.out.println("Returning User Name = "+userName);
            return userName;
        }).exceptionally(ex -> {
            System.out.println("Exception while fetching User Name: " + ex.getMessage());
            return null;
        }).thenComposeAsync(userName -> {
            if (userName == null) throw new IllegalStateException("User Name can't be null");
            return CompletableFuture.supplyAsync(() -> {
                try {
                    System.out.println("Fetching Order for User Name = "+userName);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "Orders for " + userName + ": [Order1, Order2]";
            });
        }).exceptionally(ex -> {
            System.out.println("Exception while fetching User Orders: " + ex.getMessage());
            return null;
        }).thenAcceptAsync(finalResult -> {
            if (finalResult != null) {
                System.out.println("Result: " + finalResult);
            }
        });
    }

    public static void blocking() {
        System.out.println("Completable Future Example =======================================");
        // Step 1: Fetch User ID
        CompletableFuture<Integer> fetchUserId = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Do database operations
            return 42; // User ID
        }).exceptionally(ex -> {
            System.out.println("Exception while fetching User ID: " + ex.getMessage());
            return null;
        });

        // Step 2: Fetch User Name
        CompletableFuture<String> fetchUserName = fetchUserId.thenApplyAsync(userId -> {
            if (userId == null) throw new IllegalStateException("User ID can't be null");
            // do some database operations.
            return "User_" + userId;
        }).exceptionally(ex -> {
            System.out.println("Exception while fetching User Name: " + ex.getMessage());
            return null;
        });

        // Step 3: Fetch User Orders
        CompletableFuture<String> fetchUserOrders = fetchUserName.thenComposeAsync(userName -> {
            if (userName == null) throw new IllegalStateException("User Name can't be null");
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "Orders for " + userName + ": [Order1, Order2]";
            });
        }).exceptionally(ex -> {
            System.out.println("Exception while fetching User Orders: " + ex.getMessage());
            return null;
        });

        // get() should not be used in a Production Environment
        try {
            fetchUserOrders.thenAcceptAsync(finalResult -> {
                if (finalResult != null) {
                    System.out.println("Result: " + finalResult);
                }
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
