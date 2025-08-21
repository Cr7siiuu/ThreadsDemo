package com.exercice.threadsDemo.repositories;

import com.exercice.threadsDemo.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagesRepository extends JpaRepository<Messages,Long> {
}
