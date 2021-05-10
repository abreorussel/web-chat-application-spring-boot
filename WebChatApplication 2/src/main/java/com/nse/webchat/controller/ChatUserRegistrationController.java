package com.nse.webchat.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.nse.webchat.entity.ChatUser;
import com.nse.webchat.exception.ChatUserBadCredentialException;
import com.nse.webchat.exception.ChatUserDisabledException;
import com.nse.webchat.exception.ChatUserValidationException;
import com.nse.webchat.service.IChatUserService;
import com.nse.webchat.utility.ChatUserConstants;

@RestController
public class ChatUserRegistrationController {

	@Autowired
	IChatUserService userService;

//	@Autowired
//	private AuthenticationManager authenticationManager;
//
//	@Autowired
//	private JwtTokenUtil jwtTokenUtil;
//
//	@Autowired
//	private UserDetailsService jwtInMemoryUserDetailsService;

	@Autowired
	HttpSession httpSession;

	@PostMapping("/user-login")
	public ModelAndView loginUser(ChatUser user) {

		boolean valid = true;
		Map<String, Object> response = new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView();

//		try {
//			authenticate(user.getUserEmailId(), user.getUserPassword());
//		} catch (ChatUserDisabledException | ChatUserBadCredentialException e) {
////			response.put(ChatUserConstants.Response.MESSAGE, e.getMessage());
//			mv.addObject(ChatUserConstants.Response.MESSAGE, e.getMessage());
//			valid = false;
//		}

		valid = userService.authenticateUser(user);

		if (!valid) {
//			response.put(ChatUserConstants.Response.ERROR, true);
//			response.put(ChatUserConstants.Response.MESSAGE, ChatUserConstants.Registration.WRONG_CREDENTIALS);
//			response.put(ChatUserConstants.Validation.USER, user);
			mv.addObject(ChatUserConstants.Response.MESSAGE, ChatUserConstants.Registration.WRONG_CREDENTIALS);
			mv.addObject(ChatUserConstants.Response.ERROR, true);
			mv.addObject(ChatUserConstants.Validation.USER, user);

			mv.setViewName("login");
			return mv;
		} else {

//			final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(user.getUserEmailId());
//
//			final String token = jwtTokenUtil.generateToken(userDetails);
//			System.out.println("Token");
//			System.out.println(token);

//			mv.addObject("token", "Bearer " + token);

//			httpSession.setAttribute("Authorization", "Bearer " + token);

			user = userService.getUserByEmail(user.getUserEmailId());
			System.out.println(user);
			httpSession.setAttribute("user", user);
		}

//		response.put(ChatUserConstants.Response.ERROR, false);
//		response.put(ChatUserConstants.Response.MESSAGE, ChatUserConstants.Registration.LOGIN_SUCCESS);
//		response.put(ChatUserConstants.Validation.USER, user);

		mv.addObject(ChatUserConstants.Response.ERROR, false);
		mv.addObject(ChatUserConstants.Response.MESSAGE, ChatUserConstants.Registration.LOGIN_SUCCESS);
		mv.addObject(ChatUserConstants.Validation.USER, user);

		mv.setViewName("index");
		return mv;
	}

	@PostMapping("/user-register")
	public ModelAndView saveUser(ChatUser user, @RequestParam("userImageFile") MultipartFile userImageFile) {

		boolean valid = true;
		Map<String, Object> errorResponse = new HashMap<String, Object>();
		ChatUser addedUser = null;
		ModelAndView mv = new ModelAndView();

		try {
			userService.validateName(user.getUserFullName());
		} catch (ChatUserValidationException e) {
			errorResponse.put(ChatUserConstants.Validation.USER_FULL_NAME, e.getMessage());
			errorResponse.put("from", "nameValidation");
			valid = false;
		}

		try {
			userService.validateEmail(user.getUserEmailId());
		} catch (ChatUserValidationException e) {
			errorResponse.put(ChatUserConstants.Validation.USER_EMAIL_ID, e.getMessage());
			errorResponse.put("from", "emailValidation");
			valid = false;
		}

		try {
			userService.validateDuplicateEamil(user.getUserEmailId());
		} catch (ChatUserValidationException e) {
			errorResponse.put(ChatUserConstants.Validation.USER_EMAIL_ID, e.getMessage());
			errorResponse.put("from", "duplicateValidation");
			valid = false;
		}

		try {
			userService.validatePhoneNumber(user.getUserPhoneNumber());
		} catch (ChatUserValidationException e) {
			errorResponse.put(ChatUserConstants.Validation.USER_PHONE, e.getMessage());
			errorResponse.put("from", "phoneValidation");
			valid = false;
		}

		if (valid) {

			try {
				user.setUserName(user.getUserEmailId());
				addedUser = userService.saveUser(user, userImageFile);
			} catch (IOException e) {
				errorResponse.put(ChatUserConstants.Validation.USER_DISPLAY_PICTURE, e.getMessage());
				valid = false;
			}

		}

		if (!valid) {
			errorResponse.put(ChatUserConstants.Response.ERROR, true);
			errorResponse.put(ChatUserConstants.Response.MESSAGE, ChatUserConstants.Registration.WRONG_DATA);

			errorResponse.forEach((key, value) -> {
				mv.addObject(key.toString(), value);
				System.out.println(value);
			});
			mv.addObject(ChatUserConstants.Validation.USER, user);

			mv.setViewName("register");

			return mv;

		}

		mv.addObject(ChatUserConstants.Registration.REGISTRATION_SUCCESSFULL,
				ChatUserConstants.Registration.REGISTRATION_SUCCESSFULL_MESSAGE);

		mv.setViewName("login");

		return mv;

	}
//
//	private void authenticate(String username, String password)
//			throws ChatUserDisabledException, ChatUserBadCredentialException {
//		Objects.requireNonNull(username);
//		Objects.requireNonNull(password);
//
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		} catch (DisabledException e) {
//			throw new ChatUserDisabledException(ChatUserConstants.User.USER_DISABLED);
//		} catch (BadCredentialsException e) {
//			throw new ChatUserBadCredentialException(ChatUserConstants.Registration.WRONG_CREDENTIALS);
//		}
//	}

}
