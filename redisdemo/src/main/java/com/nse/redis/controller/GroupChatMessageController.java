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
import com.nse.redis.entity.GroupChatMessage;
import com.nse.redis.service.GroupServiceImpl;



@RestController
@RequestMapping("/group")
public class GroupChatMessageController {
	
private final Logger logger = LoggerFactory.getLogger(GroupChatMessageController.class);
	
	@Autowired
	private GroupServiceImpl groupService;

	//Used
	@PostMapping("/addGroupMessage")
	//@CachePut(value = "groupchat", key = "#groupchatmessage. groudMessageId")
	public GroupChatMessage create(@RequestBody GroupChatMessage groupchatmessage) {
		logger.debug(" >> GroupChatMessageController : /groupchatmessage : ", groupchatmessage.toString());
		return groupService.create(groupchatmessage);
	}
	
	
	// USed
	@GetMapping("/getAllChatMessages/{groupId}")
	//@Cacheable (value = "groupMessage", key = "#groupId")
	public List<GroupChatMessage> getAllGroupChatMessages(@PathVariable("groupId") long groupId) {
		
		return groupService.getAllGroupChatMessages(groupId);
	}
	
	

}
