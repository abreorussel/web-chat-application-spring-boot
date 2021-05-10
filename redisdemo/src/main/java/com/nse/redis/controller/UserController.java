package com.nse.redis.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.nse.redis.entity.ChatUser;
import com.nse.redis.entity.GroupInfo;
import com.nse.redis.service.UserServiceImpl;


@RestController
@RequestMapping("/user")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserServiceImpl userService;

	@PostMapping("/addUser")
	//@CachePut(value = "users")

	public ChatUser create(@RequestBody ChatUser user) {
		logger.debug(" >> UserController : /user : ", user.toString());
		return userService.create(user);
	}

	@GetMapping("/getUserById/{id}")
	//@Cacheable(value = "users", key = "#id")
	public ChatUser getUserById(@PathVariable long id) {
		logger.debug(" >> UserController : /user/{} call : ", id);

		return userService.getUserById(id);

	}
	
	@GetMapping("/getUserByEmailId/{id}")
	//@Cacheable(value = "users", key = "#id")
	public ChatUser getUserByEmailId(@PathVariable String id) {
		logger.debug(" >> UserController : /user/{} call : ", id);

		return userService.getUserByEmail(id);

	}
	
	@GetMapping("/getUserByUserName/{id}")
	//@Cacheable(value = "users", key = "#id")
	public ChatUser getUserByUserName(@PathVariable String id) {
		logger.debug(" >> UserController : /user/{} call : ", id);

		return userService.findByUserName(id);

	}


	@GetMapping("/getAllUsers")
	//@Cacheable(value = "users")
	public List<ChatUser> getAll() {
		logger.debug(" >> UserController : /users : ");

		return userService.getAll();
	}

	@PutMapping("/updateUserById/{id}")
	//@CacheEvict(value = "users",allEntries = true)
	public ChatUser updateUser(@RequestBody ChatUser user) {
		logger.debug(" >> UserController : /update : ", user.toString());
		return userService.update(user);
	}
	
	@GetMapping("/get-all-groups/{userName}")
	public Set<GroupInfo> getAllGroups(@PathVariable String userName){
		// Setting the value for the key from DB to Redis
		
		return userService.findByUserName(userName).getGroupInfos();
		
	}

}
