package com.nse.redis.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nse.redis.entity.Conversation;
import com.nse.redis.repository.ConversationRepository;

@Service
public class ConversationServiceImpl implements ConversationService{
	private final Logger logger = LoggerFactory.getLogger(ConversationServiceImpl.class);

	@Autowired
	private ConversationRepository conversationRepository;
	
	

	
	@Override
	public Conversation addConversation(Conversation conversation) {
		 return conversationRepository.save(conversation);
	}

	

	public Conversation getConversationById(long fromUser,long fromtoUser) {
		logger.debug(" >> ConversationService : Entering getConversation");
		
		return conversationRepository.findConversation(fromUser, fromtoUser).orElse(null);

		
	}

}
