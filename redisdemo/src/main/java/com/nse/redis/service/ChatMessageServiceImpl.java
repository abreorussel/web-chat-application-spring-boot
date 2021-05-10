package com.nse.redis.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nse.redis.entity.ChatMessage;

import com.nse.redis.repository.ChatMessageRepository;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

	private final Logger logger = LoggerFactory.getLogger(ChatMessageServiceImpl.class);
	
	@Autowired
	private ChatMessageRepository chatmessageRepository;

	
	@Override
	public ChatMessage create(ChatMessage chatmessage) {
		logger.debug(" >> ChatMessageService : Entering create");

		ChatMessage chatmessageToRet = chatmessageRepository.save(chatmessage);
		logger.debug(" << ChatMessageService : Exiting create");
		return chatmessageToRet;
	}

	@Override
	public List<ChatMessage> getChatMessageById(long id) {
		logger.debug(" >> ChatMessageService : Entering getChatMessage");
		return chatmessageRepository.findByConversationId(id);



}

	@Override
	public void delete(Long id) {
		chatmessageRepository.deleteById(id);
		
	}

	public List<ChatMessage> getAll() {
		logger.debug(" >> ChatMessageService : Entering getAll");
		logger.debug(" << ChatMessageService : Exiting getAll");
		return chatmessageRepository.findAll();
	}

	
}
