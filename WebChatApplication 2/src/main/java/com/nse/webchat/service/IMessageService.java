package com.nse.webchat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nse.webchat.entity.ChatMessage;
import com.nse.webchat.model.ChatMessageVO;



@Service
public interface IMessageService {

	List<ChatMessage> getAllMessages(long conversationId);

	List<ChatMessage> getMessagesByIds(long fromUser, long toUser);

	ChatMessage addMessage(ChatMessageVO message, long chatId);
	void notifyReceiver(String topic, ChatMessageVO message);

}
