package com.nse.redis.service;

import java.util.List;

import com.nse.redis.entity.ChatUser;

public interface UserService {

	ChatUser create(ChatUser user);

	ChatUser update(ChatUser user);

	List<ChatUser> getAll();

	ChatUser getUserByEmail(String email);

	ChatUser findByUserName(String userName);

	ChatUser getUserById(long id);

}
