package com.nse.webchat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nse.webchat.entity.ChatMessage;
import com.nse.webchat.entity.ChatUser;
import com.nse.webchat.entity.Conversation;
import com.nse.webchat.model.ChatMessageVO;



@Service
public interface IChatService {
	
	
	List<ChatUser> getAllUsers();
	ChatUser getUserById(long userId);
	ChatUser addUser(ChatUser user);
	
	Conversation getConversation(long fromUser,long toUser);
	Conversation addConversation(long fromUser,long toUser);
	ChatMessage addMessage(ChatMessageVO message,long chatId);
	List<ChatMessage> getAllMessages(long conversationId);
	List<ChatMessage> getMessagesByIds(long fromUser,long toUser);
	
	void sendMessage(ChatMessage message);
	ChatMessageVO saveAttachment(long chatId, ChatMessageVO chatMsg, MultipartFile attachement);
	

}
