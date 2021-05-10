package com.nse.webchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nse.webchat.entity.ChatMessage;



@Repository
public interface IChatMessageRepository extends JpaRepository<ChatMessage, Long>{
List<ChatMessage> findByConversationId(long conversationId);
}
