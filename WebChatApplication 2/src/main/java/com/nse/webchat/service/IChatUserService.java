package com.nse.webchat.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nse.webchat.entity.ChatUser;
import com.nse.webchat.exception.ChatUserValidationException;

public interface IChatUserService {

	ChatUser getUserById(Long userId);

//	ChatUser getUserByUserName(String userFullName);

	ChatUser saveUser(ChatUser user, MultipartFile imageFile) throws IOException;

	void validateName(String userFullName);

//	
	void validateEmail(String userEmailId);

	public void validateDuplicateEamil(String userEmailId);

	void validatePhoneNumber(String userPhoneNumber);

//	void validateDuplicateName(String userFullName);

	boolean authenticateUser(ChatUser user);

	ChatUser getUserByEmail(String email);

	List<ChatUser> getUsers();

	ChatUser findByUserName(String userName);

	List<ChatUser> findAllUsers();

	ChatUser finduserById(long userId);

}
