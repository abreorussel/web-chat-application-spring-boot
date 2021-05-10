package com.nse.redis.service;

import java.util.List;

import com.nse.redis.entity.ChatMessage;


public interface ChatMessageService {

	ChatMessage create(ChatMessage chatmessage);
	
	void delete(Long messageid);
	
	List<ChatMessage> getChatMessageById(long id);
	
}
