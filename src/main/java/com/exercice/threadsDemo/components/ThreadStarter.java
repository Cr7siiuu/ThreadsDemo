package com.exercice.threadsDemo.components;

import com.exercice.threadsDemo.threads.EvenNumberThread;
import com.exercice.threadsDemo.threads.OddNumberThread;
import com.exercice.threadsDemo.threads.RandomNumberThread;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;

@Component
//@DependsOn(value = "logExecutor")
public class ThreadStarter implements ApplicationRunner {

    @Qualifier(value = "logExecutor")
    private ThreadPoolTaskExecutor poolTaskExecutor;


    private final int nbRecherche;

    private List<String> listMessage = new ArrayList<>();

    private final CountDownLatch latch;

    public ThreadStarter(ThreadPoolTaskExecutor poolTaskExecutor,@Value(value = "${thread.recherche}") int nbRecherche) {
        this.poolTaskExecutor = poolTaskExecutor;
        this.nbRecherche = nbRecherche;
        this.latch = new CountDownLatch(nbRecherche);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        RandomNumberThread randomThread = new RandomNumberThread();
            for (int i = 0; i<nbRecherche; i++){
                CompletableFuture<Long> nb = poolTaskExecutor.submitCompletable(randomThread);
                OddNumberThread oddThread = new OddNumberThread(nb.get(),i);
                EvenNumberThread evenThread = new EvenNumberThread(nb.get(),i);
                CompletableFuture<String> oddMessage = poolTaskExecutor.submitCompletable(oddThread);
                CompletableFuture<String> evenMessage = poolTaskExecutor.submitCompletable(evenThread);
                listMessage.add(oddMessage.get());
                listMessage.add(evenMessage.get());
                latch.countDown();
            }
    }

    public CompletableFuture<List<String>> computeList() throws InterruptedException {
        return poolTaskExecutor.submitCompletable(()->{
                try {
                    latch.await();
                    return listMessage;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
        });
    }
}
