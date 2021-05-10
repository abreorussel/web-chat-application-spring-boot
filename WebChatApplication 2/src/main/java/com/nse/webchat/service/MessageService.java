package com.nse.webchat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nse.webchat.entity.ChatMessage;
import com.nse.webchat.entity.ChatMessage.Status;
import com.nse.webchat.model.ChatMessageVO;
import com.nse.webchat.repository.IChatMessageRepository;

import reactor.core.publisher.Mono;






@Component
public class MessageService implements IMessageService {
	
	@Autowired
	private WebClient.Builder webClientBuilder;

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@Autowired
	private IChatService service;
	
	@Autowired
	IChatMessageRepository messageRepository;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ChatMessage> getAllMessages(long conversationId) {
		
		return webClientBuilder.build().get().uri("http://localhost:9002/chatMessage/getChatMessagesByConversationId/"+conversationId)
				.retrieve().bodyToMono(List.class).block();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ChatMessage> getMessagesByIds(long fromUser, long toUser) {
		long conversationId = service.getConversation(fromUser, toUser).getConversationId();
		if (conversationId<0) {
			return null;
		}
		return webClientBuilder.build().get().uri("http://localhost:9002/chatMessage/getChatMessagesByConversationId/"+conversationId)
				.retrieve().bodyToMono(List.class).block();
	}

	
	@Override
	public ChatMessage addMessage(ChatMessageVO message,long chatId) {
		
		ChatMessage chatMessage=new ChatMessage();
		chatMessage.setConversationId(chatId);
		chatMessage.setMessageAuthorId(message.getAuthor());
		chatMessage.setMessageContent(message.getContent());
		chatMessage.setStatus(Status.READ);
		
		return webClientBuilder.build().post().uri("http://localhost:9002/chatMessage/addChatMessage")
				.body(Mono.just(chatMessage),ChatMessage.class)
				.retrieve().bodyToMono(ChatMessage.class).block();
	}

	@Override
	public void notifyReceiver(String topic, ChatMessageVO message) {
		
		simpMessagingTemplate.convertAndSend(topic, message);
	}

}
