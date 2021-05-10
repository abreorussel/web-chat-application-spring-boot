package com.nse.redis.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nse.redis.entity.ChatUser;
import com.nse.redis.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public ChatUser create(ChatUser user) {
		logger.debug(" >> UserService : Entering create");

		ChatUser userToRet = userRepository.save(user);
		logger.debug(" << UserService : Exiting create");
		return userToRet;
	}

	@Override
	public ChatUser getUserById(long id) {
		logger.debug(" >> UserService : Entering getUser");
		Optional<ChatUser> userOp = userRepository.findById(id);

		if (userOp != null) {
			logger.debug(" << UserService : Exiting getUser");
			return userOp.get();
		} else {
			logger.debug(" << UserService : No Such User Exists : Exiting getUser");
			return null;
		}

	}


	@Override
	public List<ChatUser> getAll() {
		logger.debug(" >> UserService : Entering getAll");
		logger.debug(" << UserService : Exiting getAll");
		return userRepository.findAll();
	}

	@Override
	public ChatUser update(ChatUser user) {
		logger.debug(">> UserService : Entering update");
		long id = user.getUserId();
		ChatUser userInDb = getUserById(id);
		if (userInDb != null) {
			logger.debug(">> UserService : User updated : Exiting update");
			return create(user);
		} else {
			logger.debug(">> UserService : User with this id does not exist : Exiting update");
			return null;
		}

	}

	@Override
	public ChatUser getUserByEmail(String email) {
		
		return userRepository.findByUserEmailIdIgnoreCase(email);
	}

	@Override
	public ChatUser findByUserName(String userName) {
		
		return userRepository.findByUserName(userName);
	}

}
