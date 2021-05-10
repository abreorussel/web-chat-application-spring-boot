package com.nse.redis.service;

import java.util.List;

import com.nse.redis.entity.Conversation;



public interface ConversationService {
	
	Conversation getConversationById(long fromUser,long toUser);
	Conversation addConversation(Conversation conversation);

}
