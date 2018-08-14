package com.example.Repositories;

import com.example.Entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {

    Message findMessageById(Long id);
}
