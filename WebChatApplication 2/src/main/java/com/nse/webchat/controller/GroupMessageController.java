package com.nse.webchat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nse.webchat.entity.ChatUser;
import com.nse.webchat.entity.GroupChatMessage;
import com.nse.webchat.service.IChatService;
import com.nse.webchat.service.IChatUserService;
import com.nse.webchat.service.IGroupChatMessagingService;

@RestController
public class GroupMessageController {
	
	
	@Autowired
	IGroupChatMessagingService service;
	
	@Autowired
	IChatUserService userService;
	
	@Autowired
	HttpSession httpSession;
	
	@GetMapping("/getGroupMessages/{groupId}")
	public List<GroupChatMessage> getGroupMessages(@PathVariable long groupId){
		return service.getAllGroupChatMessages(groupId);
	}
	
	@RequestMapping("/getGroupChatPage")
	public ModelAndView getGroupPage(HttpServletRequest request,ModelAndView mv) {
//		long userId = Long.parseLong(request.getParameter("myId"));
//		System.out.println("In Group Controller "+userId);
		
//		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
//		String userName=auth.getName();
//		
//		ChatUser chatUser= userService.getUserByEmail(userName);
		ChatUser chatUser = (ChatUser) httpSession.getAttribute("user");
		
		mv.addObject("user",chatUser);
		mv.setViewName("group");
		return mv;
	}

	@RequestMapping("/getChatPage")
	public ModelAndView getChatPage(HttpServletRequest request,ModelAndView mv) {
//		long userId = Long.parseLong(request.getParameter("myId"));
//		System.out.println("In Group Controller "+userId);

//		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
//		String userName=auth.getName();
//
//		ChatUser chatUser= userService.getUserByEmail(userName);
		ChatUser chatUser = (ChatUser) httpSession.getAttribute("user");

		mv.addObject("user",chatUser);
		mv.setViewName("index");
		return mv;
	}
	
	
}
