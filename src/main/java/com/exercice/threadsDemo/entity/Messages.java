package com.exercice.threadsDemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Entity
@Log4j2
@Getter
@Setter
@NoArgsConstructor
public class Messages {
    @Id
    @GeneratedValue
    private long id;
    private String message;

    public Messages(String message) {
        this.message = message;
    }

}
