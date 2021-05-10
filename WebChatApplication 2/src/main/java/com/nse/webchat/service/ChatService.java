package com.nse.webchat.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import com.nse.webchat.entity.Attachment;
import com.nse.webchat.entity.ChatMessage;
import com.nse.webchat.entity.ChatMessage.Status;
import com.nse.webchat.entity.ChatUser;
import com.nse.webchat.entity.Conversation;
import com.nse.webchat.model.ChatMessageVO;
import com.nse.webchat.repository.IAttachmentRepository;
import com.nse.webchat.repository.IChatMessageRepository;
import com.nse.webchat.repository.IChatUserRepository;
import com.nse.webchat.repository.IConversationRepository;
import com.nse.webchat.utility.FileUploadUtil;

import reactor.core.publisher.Mono;

@Component
public class ChatService implements IChatService {

	@Autowired
	WebClient.Builder webClientBuilder;

	@Autowired
	IConversationRepository conversationRepository;

	@Autowired
	IChatUserRepository userRepository;

	@Autowired
	IChatMessageRepository messageRepository;

	@Autowired
	IAttachmentRepository attachmentRepository;

	@SuppressWarnings("unchecked")
	@Override
	public List<ChatUser> getAllUsers() {

		return webClientBuilder.build().get().uri("http://localhost:9002/user/getAllUsers").retrieve()
				.bodyToMono(List.class).block();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChatMessage> getAllMessages(long conversationId) {

		return webClientBuilder.build().get().uri("http://localhost:9002/chatMessage/getChatMessagesByConversationId/"+conversationId)
				.retrieve().bodyToMono(List.class).block();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChatMessage> getMessagesByIds(long fromUser, long toUser) {
		long conversationId = getConversation(fromUser, toUser).getConversationId();
		if (conversationId < 0) {
			return null;
		}
		return webClientBuilder.build().get().uri("http://localhost:9002/chatMessage/getChatMessagesByConversationId/"+conversationId)
				.retrieve().bodyToMono(List.class).block();
	}

	@Override
	public ChatMessage addMessage(ChatMessageVO message, long chatId) {

		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setConversationId(chatId);
		chatMessage.setMessageAuthorId(message.getAuthor());
		chatMessage.setMessageContent(message.getContent());
		// chatMessage.setSentAt(LocalDateTime.now());
		chatMessage.setStatus(Status.READ);

		return webClientBuilder.build().post().uri("http://localhost:9002/chatMessage/addChatMessage")
				.body(Mono.just(chatMessage),ChatMessage.class)
				.retrieve().bodyToMono(ChatMessage.class).block();
	}

	@Override
	public ChatUser addUser(ChatUser user) {
		user.setUserCreationDate(LocalDateTime.now());
		return webClientBuilder.build().post().uri("http://localhost:9002/user/addUser")
				.body(Mono.just(user),ChatUser.class)
				.retrieve().bodyToMono(ChatUser.class).block();
	}

	@Override
	public ChatUser getUserById(long userId) {
		return webClientBuilder.build().get().uri("http://localhost:9002/user/getUserById/"+userId)
				.retrieve().bodyToMono(ChatUser.class).block();
	}

	@Override
	public Conversation addConversation(long fromUser, long toUser) {
		Conversation conversation = new Conversation();
		conversation.setFromUser(fromUser);
		conversation.setToUser(toUser);
		conversation.setCreationDate(LocalDateTime.now());

		return webClientBuilder.build().post().uri("http://localhost:9002/conversation/addConversation")
				.body(Mono.just(conversation),Conversation.class)
				.retrieve().bodyToMono(Conversation.class).block();
	}

	@Override
	public Conversation getConversation(long fromUser, long toUser) {
		
		Conversation conversation=webClientBuilder.build().get().uri("http://localhost:9002/conversation/getConversationById/"+fromUser+"/"+toUser)
		.retrieve().bodyToMono(Conversation.class).block();
		if (conversation!= null) {

			return conversation;
		}
		System.out.println(fromUser + "---" + toUser);

		return addConversation(fromUser, toUser);
	}

	@Override
	public ChatMessageVO saveAttachment(long chatId, ChatMessageVO chatMsg, MultipartFile uploadedAttachment) {

		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setConversationId(chatId);
		chatMessage.setMessageAuthorId(chatMsg.getAuthor());
		chatMessage.setStatus(Status.READ);
		chatMessage.setMessageContent(chatMsg.getContent());
		String uploadFolder = "src/main/resources/static/attachment";
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
		String formatDateTime = LocalDateTime.now().format(format);
		String fileName = chatMsg.getAuthor() + "_" + formatDateTime + "_"+ StringUtils.cleanPath(uploadedAttachment.getOriginalFilename());
		String filePath = uploadFolder + "/" + fileName;

		FileUploadUtil utilFile = new FileUploadUtil();

//		Attachment attachment = new Attachment();
//		attachment.setFileName(fileName);
//		attachment.setFilePath(filePath);
//		attachment.setUploadedByUserId(chatMsg.getAuthor());
//		Attachment savedAttachment = attachmentRepository.save(attachment);

		try {
			utilFile.saveAttachment(uploadFolder, fileName, uploadedAttachment);
			chatMessage.setAttachmentPath(filePath);
			chatMsg.setFilePath(filePath);
		} catch (IOException e) {
			chatMessage.setMessageContent("Error Sending attachment!");
		}

		ChatMessage message = webClientBuilder.build().post().uri("http://localhost:9002/chatMessage/addChatMessage")
				.body(Mono.just(chatMessage),ChatMessage.class)
				.retrieve().bodyToMono(ChatMessage.class).block();
		System.out.println(message);
		return chatMsg;
	
	}
	

	@Override
	public void sendMessage(ChatMessage message) {
		// TODO Auto-generated method stub

	}

}
