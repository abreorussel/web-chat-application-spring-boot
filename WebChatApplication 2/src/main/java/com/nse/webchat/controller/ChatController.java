package com.nse.webchat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.nse.webchat.entity.Conversation;
import com.nse.webchat.model.ChatMessageVO;
import com.nse.webchat.service.IChatService;
import com.nse.webchat.service.IGroupChatMessagingService;
import com.nse.webchat.service.MessageService;
import com.nse.webchat.syncconfig.RedisUtil;




@RestController
public class ChatController {

	//@Autowired
	//private SimpMessagingTemplate simpleMsgTmp;
	@Autowired
	private MessageService msgService;
	
	@Autowired
	private IChatService service;
	
	@Autowired
	private IGroupChatMessagingService groupService;
	
	@MessageMapping("/chat/{chatId}")
	public void sendMessage(@DestinationVariable String chatId, ChatMessageVO chatMsg ) {
		
		//Using broker now
		//simpleMsgTmp.convertAndSend("/topic/messages/"+chatId, chatMsg);
		msgService.addMessage(chatMsg,Long.parseLong(chatId));
	
		msgService.notifyReceiver("/topic/messages/"+chatId, chatMsg);
	}
	
	@PostMapping("/chat/attachment/{chatId}")
	public void saveAttachment(@PathVariable long chatId, ChatMessageVO chatMsg, @RequestParam("attachment") MultipartFile attachement) {
		
//		System.out.println(attachement.getOriginalFilename());
		if(chatMsg.getContent().isBlank())
			chatMsg.setContent("Media");
		ChatMessageVO updatedChatMsg = service.saveAttachment(chatId,chatMsg,attachement);
		
		msgService.notifyReceiver("/topic/messages/"+chatId, updatedChatMsg);
		
	}
	
	@GetMapping("/getConversation/{fromUserId}/{toUserId}")
	public Conversation getConversation(@PathVariable  long fromUserId, @PathVariable long toUserId) {
		System.out.println(fromUserId+" "+toUserId);
		
		Conversation conversation = service.getConversation(fromUserId, toUserId);
		
		// Setting the value for the key from DB to Redis
		
		  RedisUtil.stringSet("conversation_id:"+fromUserId
		  ,JSON.toJSONString(conversation));
		  
		  // Displaying the value from Redis for the key
		  RedisUtil.stringGet("conversation_id:"+fromUserId);
		 
		return conversation;
	}
	
	@MessageMapping("/chat/group/{chatId}")
	public void sendGroupMessage(@DestinationVariable String chatId, ChatMessageVO chatMsg ) {
		
		//Using broker now
		//simpleMsgTmp.convertAndSend("/topic/messages/"+chatId, chatMsg);
		groupService.addGroupMessage(chatMsg, Long.parseLong(chatId));
		msgService.notifyReceiver("/topic/messages/"+chatId, chatMsg);
	}
	
	@PostMapping("/groupChat/attachment/{chatId}")
	public void saveGroupAttachment(@PathVariable long chatId, ChatMessageVO chatMsg, @RequestParam("attachment") MultipartFile attachement) {

//		System.out.println(attachement.getOriginalFilename());
		ChatMessageVO updatedChatMsg = groupService.saveAttachment(chatId,chatMsg,attachement);

		msgService.notifyReceiver("/topic/messages/"+chatId, updatedChatMsg);

	}
	
}
