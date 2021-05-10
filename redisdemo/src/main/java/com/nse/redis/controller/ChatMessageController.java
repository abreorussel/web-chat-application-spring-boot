package com.nse.redis.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nse.redis.entity.ChatMessage;

import com.nse.redis.service.ChatMessageServiceImpl;

@RestController
@RequestMapping("/chatMessage")
public class ChatMessageController {

	private final Logger logger = LoggerFactory.getLogger(ChatMessageController.class);

	@Autowired
	private ChatMessageServiceImpl chatmessageService;

	@PostMapping("/addChatMessage")
	// @CacheEvict(value = "chatmessages",allEntries = true)
	public ChatMessage create(@RequestBody ChatMessage chatmessage) {
		logger.debug(" >> ChatMessageController : /chatmessage : ", chatmessage.toString());
		return chatmessageService.create(chatmessage);
	}

	@GetMapping("/getChatMessagesByConversationId/{id}")
	// @Cacheable(value = "chatmessages", key = "#id")
	public List<ChatMessage> getChatMessageById(@PathVariable long id) {
		logger.debug(" >> ChatMessageController : /chatmessage/{} call : ", id);
		return chatmessageService.getChatMessageById(id);

	}

}
