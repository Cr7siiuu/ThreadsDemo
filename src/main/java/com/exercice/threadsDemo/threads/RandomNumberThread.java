package com.exercice.threadsDemo.threads;

import java.util.Random;
import java.util.concurrent.Callable;

public class RandomNumberThread implements Callable<Long> {
    private Random random = new Random();
    @Override
    public Long call() throws InterruptedException {
        //Long nb = new Random().nextLong();
        Long nb = this.random.nextLong(-1000,1000000);
        Thread.sleep(500);
        return Math.abs(nb);
    }
}
