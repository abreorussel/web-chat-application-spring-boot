package com.nse.redis.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nse.redis.entity.Conversation;
import com.nse.redis.entity.ChatUser;
import com.nse.redis.service.ConversationServiceImpl;
import com.nse.redis.service.UserServiceImpl;

@RestController
@RequestMapping("/conversation")
public class ConversationController {
	private final Logger logger = LoggerFactory.getLogger(ConversationController.class);
	@Autowired
	private ConversationServiceImpl conversationService;
	
	@PostMapping("/addConversation")
	//@CacheEvict(value = "conversations",allEntries = true)
	public Conversation addConversation(@RequestBody Conversation conversation) {
		logger.debug(" >> ConversationController : /conversation/{} call : ");

		return conversationService.addConversation(conversation);

	}
	
	@GetMapping("/getConversationById/{fromUser}/{toUser}")
	//@Cacheable("conversations")
	public Conversation getConversationById(@PathVariable long fromUser,@PathVariable long toUser) {
		logger.debug(" >> ConversationController : /conversation : ");

		return conversationService.getConversationById(fromUser,toUser);
	}
}
