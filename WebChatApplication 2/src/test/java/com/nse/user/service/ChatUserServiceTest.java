package com.nse.user.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nse.webchat.ChatApplicationUserApplication;
import com.nse.webchat.entity.ChatUser;
import com.nse.webchat.exception.ChatUserValidationException;
import com.nse.webchat.service.IChatUserService;

@SpringBootTest(classes=ChatApplicationUserApplication.class)
@ExtendWith(SpringExtension.class)
class ChatUserServiceTest {

	@Autowired
	IChatUserService userService;
	
	ChatUser user;
	
//	@Test
//	void testSaveUser() {
//		fail("Not yet implemented");
//	}

	@Test
	void testAuthenticateUser() {
		user = new ChatUser();
		user.setUserEmailId("sagar@gmail.com");
		user.setUserPassword("a");
		user.setUserPhoneNumber("9876543210");
		Assertions.assertTrue(userService.authenticateUser(user));
	}

	@Test
	void testAuthenticateUserNotPresent() {
		user = new ChatUser();
		user.setUserEmailId("sagarAmbilpure@gmail.com");
		user.setUserPassword("a");
		user.setUserPhoneNumber("9876543210");
		Assertions.assertFalse(userService.authenticateUser(user));
	}
	
	@Test
	void testValidateEmail() {
		user = new ChatUser();
		user.setUserEmailId("w43d3--");
		user.setUserPassword("a");
		user.setUserPhoneNumber("9876543210");
		Assertions.assertThrows(ChatUserValidationException.class, () -> userService.validateEmail(user.getUserEmailId()));
	
	}

	@Test
	void testValidateDuplicateEamil() {

		user = new ChatUser();
		user.setUserEmailId("akt15259@gmail.com");
		user.setUserPassword("a");
		user.setUserPhoneNumber("9876543210");
		Assertions.assertThrows(ChatUserValidationException.class, () -> userService.validateDuplicateEamil(user.getUserEmailId()));
	}

	@Test
	void testValidatePhoneNumber() {

		user = new ChatUser();
		user.setUserEmailId("w43d3--");
		user.setUserPassword("a");
		user.setUserPhoneNumber("4876543210");
		Assertions.assertThrows(ChatUserValidationException.class, () -> userService.validateEmail(user.getUserEmailId()));
	}

}
