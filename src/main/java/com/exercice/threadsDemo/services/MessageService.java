package com.exercice.threadsDemo.services;

import com.exercice.threadsDemo.components.ThreadStarter;
import com.exercice.threadsDemo.entity.Messages;
import com.exercice.threadsDemo.repositories.MessagesRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Log4j2
public class MessageService {
    private final MessagesRepository messagesRepository;

    private final ThreadStarter threadStarter;

    @Qualifier(value = "logExecutor")
    private ThreadPoolTaskExecutor poolTaskExecutor;

    private Boolean done = false;
    private Random random = new Random();


    public MessageService(MessagesRepository messagesRepository, ThreadStarter threadStarter) {
        this.messagesRepository = messagesRepository;
        this.threadStarter = threadStarter;
    }

    public CompletableFuture<Messages> addMessage(Messages messages){
        messages.setId(random.nextInt());
        CompletableFuture<Messages> futureOne = CompletableFuture.supplyAsync(()->
            messagesRepository.save(messages)
        );
        CompletableFuture<Void> futureTwo = CompletableFuture.runAsync(()->log.info("Message : {}",messages.getMessage()));
        return new CompletableFuture<Messages>();
    }

    public CompletableFuture<List<Messages>> getAllMessages(){
       CompletableFuture<List<Messages>> future = CompletableFuture.supplyAsync(()->
               messagesRepository.findAll());
        return future;
    }

    @Scheduled(cron = "${cron.message}")
    public void computeMessage() throws InterruptedException, ExecutionException {
        List<String> listAutoMessage = threadStarter.computeList().get();
        if (!done){
            for (String message : listAutoMessage) {
                Messages messageToAdd = new Messages(message);
                messagesRepository.save(messageToAdd);
                System.out.println(message);
            }
            done = true;
        }
    }
}
