package com.nse.webchat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nse.webchat.entity.ChatMessage;
import com.nse.webchat.model.ChatMessageVO;
import com.nse.webchat.service.IMessageService;

@RestController
@RequestMapping("/message")
public class ChatMessageController {

	@Autowired
	IMessageService msgService;

	@PostMapping("/addMessage/{chatId}")
	public ChatMessage addMessage(@RequestBody ChatMessageVO message, @PathVariable long chatId) {
		return msgService.addMessage(message, chatId);
	}

	@GetMapping("/getMessages/{fromUserId}/{toUserId}")
	public List<ChatMessage> getMessages(@PathVariable long fromUserId, @PathVariable long toUserId) {
		return msgService.getMessagesByIds(fromUserId, toUserId);
	}

	@GetMapping("/getAllMessages/{conversationId}")
	public List<ChatMessage> getAllMessages(@PathVariable long conversationId) {
		return msgService.getAllMessages(conversationId);
	}

}
