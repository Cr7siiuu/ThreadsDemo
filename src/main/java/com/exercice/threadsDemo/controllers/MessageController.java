package com.exercice.threadsDemo.controllers;

import com.exercice.threadsDemo.entity.Messages;
import com.exercice.threadsDemo.services.MessageService;
import org.aspectj.bridge.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping(path = "/messages")
    public ResponseEntity<CompletableFuture<Messages>> addMessage(@RequestBody Messages message){
        return new ResponseEntity<>(messageService.addMessage(message), HttpStatus.CREATED);
    }

    @PostMapping(path = "/multiples")
    public ResponseEntity<Integer> multipleCall(@RequestBody int nbCall) throws InterruptedException {
        for (int i=0; i<nbCall;i++){
            Messages message = new Messages("message test numero "+i);
            Thread.sleep(1000);
            addMessage(message);
        }
        return new ResponseEntity<>(nbCall, HttpStatus.OK);
    }


    @GetMapping(path = "/allMessages")
    public ResponseEntity<List<Messages>> getAllMessages() throws ExecutionException, InterruptedException {
        List<Messages> messages = messageService.getAllMessages().get();
        return new ResponseEntity<>(messages,HttpStatus.OK);
    }
}
