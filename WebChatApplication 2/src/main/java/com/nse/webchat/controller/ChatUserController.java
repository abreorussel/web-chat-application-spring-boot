package com.nse.webchat.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.nse.webchat.entity.ChatUser;
import com.nse.webchat.entity.GroupInfo;
import com.nse.webchat.service.IChatUserService;
import com.nse.webchat.syncconfig.RedisUtil;

@RestController
@RequestMapping("/user")
public class ChatUserController {

	@Autowired
	IChatUserService service;
	
	@Autowired
	IChatUserService userService;
	
	@Autowired
	HttpSession httpSession;


	@GetMapping("/getUserById/{userId}")
	public ChatUser getUserById(@PathVariable Long userId) {

		ChatUser user = service.getUserById(userId);

		return user;
	}
	
	@GetMapping("/getMyUserId")
	public ChatUser getMyUserId() {
//		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
//		String userName=auth.getName();
//		ChatUser chatUser=service.getUserByEmail(userName);
		ChatUser chatUser = (ChatUser) httpSession.getAttribute("user");
		
		// Setting the value for the key from DB to Redis
		/*
		 * RedisUtil.stringSet("user_id:"+chatUser.getUserId()
		 * ,JSON.toJSONString(chatUser));
		 * 
		 * // Displaying the value from Redis for the key
		 * RedisUtil.stringGet("user_id:"+chatUser.getUserId());
		 */
		
		return chatUser;
	}

	@GetMapping("/getAllUsers")
	public List<ChatUser> loginUser() {

		List<ChatUser> users = service.getUsers();

		return users;
	}
	
	@GetMapping("/get-all-groups/{userName}")
	public Set<GroupInfo> getAllGroups(@PathVariable String userName){
		// Setting the value for the key from DB to Redis
		
		  RedisUtil.stringSet("user_name:"+userName
		  ,JSON.toJSONString(userService.findByUserName(userName).getGroupInfos()));
		  
		  // Displaying the value from Redis for the key
		  RedisUtil.stringGet("user_name:"+userName);
		 
		
		  
		  
		return userService.findByUserName(userName).getGroupInfos();
		
	}
	
	

}
