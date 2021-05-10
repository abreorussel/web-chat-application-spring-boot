package com.nse.webchat.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import com.nse.webchat.entity.ChatMessage.Status;
import com.nse.webchat.entity.ChatMessage;
import com.nse.webchat.entity.ChatUser;
import com.nse.webchat.entity.GroupChatMessage;
import com.nse.webchat.entity.GroupInfo;
import com.nse.webchat.model.ChatMessageVO;
import com.nse.webchat.repository.GroupChatMessageRepository;
import com.nse.webchat.utility.FileUploadUtil;


import reactor.core.publisher.Mono;

@Service
public class GroupChatMessagingService implements IGroupChatMessagingService{

	@Autowired
	IChatUserService userService;

	@Autowired
	GroupChatMessageRepository repo;
	
	@Autowired
	WebClient.Builder webClientBuilder;
	
    @SuppressWarnings("unchecked")
	@Override
    public List<GroupChatMessage> getAllGroupChatMessages(long groupId) {
    	
    	repo.findByGroupId(groupId);
    	
    	return webClientBuilder.build().get().uri("http://localhost:9002/group/getAllChatMessages/"+groupId).retrieve()
				.bodyToMono(List.class).block();
    	
        
    }

	@Override
	public GroupChatMessage addGroupMessage(ChatMessageVO message, long chatId) {

		GroupChatMessage groupMessage = new GroupChatMessage();

		ChatUser user = userService.finduserById(message.getAuthor());

		groupMessage.setGroupId(chatId);
		groupMessage.setMessageAuthorId(message.getAuthor());
		groupMessage.setMessageAuthor(user.getUserFullName());
		groupMessage.setMessageContent(message.getContent());
		groupMessage.setStatus(Status.READ);

		//repo.save(groupMessage);
		
		return webClientBuilder.build().post().uri("http://localhost:9002/group/addGroupMessage").contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON).body(Mono.just(groupMessage), GroupChatMessage.class).retrieve()
		.bodyToMono(GroupChatMessage.class).block();
		
		
	}

	@Override
	public ChatMessageVO saveAttachment(long chatId, ChatMessageVO chatMsg, MultipartFile uploadedAttachment) {

		ChatUser user = userService.finduserById(chatMsg.getAuthor());

		GroupChatMessage groupChatMessage = new GroupChatMessage();
		groupChatMessage.setGroupId(chatId);
		groupChatMessage.setMessageAuthorId((chatMsg.getAuthor()));
		groupChatMessage.setMessageContent(chatMsg.getContent());
		groupChatMessage.setStatus(Status.READ);
		groupChatMessage.setMessageAuthor(user.getUserFullName());


		String uploadFolder = "src/main/resources/static/attachment";
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
		String formatDateTime = LocalDateTime.now().format(format);
		String fileName = chatMsg.getAuthor()+""+formatDateTime+""+ StringUtils.cleanPath(uploadedAttachment.getOriginalFilename());
		String filePath = uploadFolder+"/"+fileName;

		FileUploadUtil utilFile = new FileUploadUtil();

//		Attachment attachment = new Attachment();
//		attachment.setFileName(fileName);
//		attachment.setFilePath(filePath);
//		attachment.setUploadedByUserId(chatMsg.getAuthor());
//		Attachment savedAttachment = attachmentRepository.save(attachment);

		try {
			utilFile.saveAttachment(uploadFolder, fileName, uploadedAttachment);
			groupChatMessage.setAttachmentPath(filePath);
			chatMsg.setFilePath(filePath);
		} catch (IOException e) {
			groupChatMessage.setMessageContent("Error Sending attachment!");
		}
		
		GroupChatMessage message = webClientBuilder.build().post().uri("http://localhost:9002/group/addGroupMessage")
				.body(Mono.just(groupChatMessage),GroupChatMessage.class)
				.retrieve().bodyToMono(GroupChatMessage.class).block();




		//GroupChatMessage message = repo.save(groupChatMessage);

		return chatMsg;
	}
    
}
