package com.nse.webchat.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.nse.webchat.entity.ChatUser;
import com.nse.webchat.entity.GroupInfo;
import com.nse.webchat.exception.GroupAlreadyExistsException;
import com.nse.webchat.exception.MemberAlreadyExistsException;
import com.nse.webchat.exception.MemberNotExistsException;
import com.nse.webchat.service.IChatUserService;
import com.nse.webchat.service.IGroupService;
import com.nse.webchat.syncconfig.RedisUtil;

@RestController
@RequestMapping("/group")
public class GroupController {

	@Autowired
	IGroupService service;

	@Autowired
	IChatUserService userService;

	@PostMapping("/add/{userName}/{groupName}")
	public GroupInfo addGroup(@PathVariable String userName, @PathVariable String groupName)
			throws GroupAlreadyExistsException {

		ModelAndView mv = new ModelAndView();

		ChatUser user = userService.findByUserName(userName);

		if (service.findByGroupName(groupName) != null) {
			// throw new GroupAlreadyExistsException("This group already exists.");
			GroupInfo emptyGroup = new GroupInfo();
			return emptyGroup;
		} else {

			GroupInfo addGroup = new GroupInfo();
			addGroup.addUser(user);
			addGroup.setGroupName(groupName);

			System.out.println(addGroup);

			GroupInfo groupResult = service.addGroup(addGroup);

			System.out.println(groupResult);

			return groupResult;
		}

	}

	@PutMapping("/add-member/{groupName}/{userName}")
	public GroupInfo addUserToGroup(@PathVariable String groupName, @PathVariable String userName)
			throws MemberAlreadyExistsException {

		GroupInfo group = service.findByGroupName(groupName);

		ChatUser user = userService.findByUserName(userName);

		ChatUser temp = user;

		temp.getGroupInfos().clear();

		// check if user is already a member of this group
		if (group.getUsers().contains(temp)) {

			System.out.println("If");

			// throw new MemberAlreadyExistsException(userName + " is already a member of "
			// + groupName);
			// return new ResponseEntity<String>("Fail" , HttpStatus.BAD_REQUEST);
			GroupInfo groupEmpty = new GroupInfo();
			return groupEmpty;
		} else {

			group.addUser(user);

			GroupInfo group1 = service.updateGroup(group);

			// return new ResponseEntity<String>("Successfully added " + userName + " to " +
			// groupName, HttpStatus.OK);
			return group1;
		}

	}

	@GetMapping("/getById/{groupId}")
	public GroupInfo findGroupById(@PathVariable long groupId) {
		// Setting the value for the key from DB to Redis

		RedisUtil.stringSet("group_id:" + groupId, JSON.toJSONString(service.findGroupById(groupId))); // // //
																										// Displaying
																										// thevalue from
																										// Redis for the
																										// key
		RedisUtil.stringGet("group_id:" + groupId);

		return service.findGroupById(groupId);
	}

	@GetMapping("/get-members/{groupName}")
	public Set<ChatUser> getMembers(@PathVariable String groupName) {

		return service.findByGroupName(groupName).getUsers();

	}

//	@DeleteMapping("/delete/{name}")
//	public ResponseEntity<String> deleteGroupByName(@PathVariable String name) {
//
//		service.deleteGroup(service.findByGroupName(name));
//
//		return new ResponseEntity<String>("Successfully Deleted " + name, HttpStatus.OK);
//	}

	@PutMapping("/remove-member/{groupName}/{userName}")
	public ResponseEntity<String> removeUserFromGroup(@PathVariable("groupName") String groupName,
			@PathVariable("userName") String userName) {

		GroupInfo group = service.findByGroupName(groupName);
		ChatUser user = userService.findByUserName(userName);

		for (ChatUser val : group.getUsers()) {
			if (user.getUserId() == val.getUserId()) {
				// remove user

				group.removeUser(user);

				if (group.getUsers().isEmpty()) {
					service.deleteGroup(group.getGroupName());
				}

				GroupInfo group1 = service.updateGroup(group);

				return new ResponseEntity<String>("Successfully removed " + userName + " from " + groupName,
						HttpStatus.OK);

			}

		}
		return new ResponseEntity<String>("Outside", HttpStatus.BAD_REQUEST);

		// new ResponseEntity<String>("Successfully removed " , HttpStatus.OK);

		// check if user exists as a member of the group
//		if (!group.getUsers().contains(user)) {
//			
//			System.out.println(group.getUsers().contains(user));
//			System.out.println(group.getUsers());
//			
//			System.out.println("If");
//			throw new MemberNotExistsException(userName + " is not a member of " + groupName);
//		} else {
//
//			System.out.println("Before remove");
//			
//			group.removeUser(user);
//
//			System.out.println("After remove"+group.getUsers());
//			
//			GroupInfo group1 = service.addGroup(group);
//
//			return new ResponseEntity<String>("Successfully removed " + userName + " from " + groupName, HttpStatus.OK);
//
//		}

	}

}
