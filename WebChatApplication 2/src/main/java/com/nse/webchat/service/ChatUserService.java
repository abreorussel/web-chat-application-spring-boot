package com.nse.webchat.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import com.nse.webchat.entity.ChatUser;
import com.nse.webchat.exception.ChatUserNotFoundException;
import com.nse.webchat.exception.ChatUserValidationException;
import com.nse.webchat.repository.IChatUserRepository;
import com.nse.webchat.utility.BCrypt;
import com.nse.webchat.utility.ChatUserConstants;
import com.nse.webchat.utility.FileUploadUtil;

import reactor.core.publisher.Mono;

@Service
public class ChatUserService implements IChatUserService {

	@Autowired
	WebClient.Builder webClientBuider;

	@Autowired
	IChatUserRepository repo;

	@Override
	public ChatUser getUserById(Long userId) {

		ChatUser chatUser = webClientBuider.build().get().uri("http://localhost:9002/user/getUserById/" + userId)
				.retrieve().bodyToMono(ChatUser.class).block();

		if (chatUser == null) {
			throw new ChatUserNotFoundException(ChatUserConstants.User.USER_NOT_FOUND + " : " + userId);
		}
		return chatUser;
	}

	@Override
	public ChatUser getUserByEmail(String email) {

		ChatUser chatUser = webClientBuider.build().get().uri("http://localhost:9002/user/getUserByEmailId/" + email)
				.retrieve().bodyToMono(ChatUser.class).block();

		if (chatUser == null) {
			throw new ChatUserNotFoundException(ChatUserConstants.User.USER_NOT_FOUND + " : " + email);
		}
		return chatUser;
	}

	@Override
	@Transactional
	public ChatUser saveUser(ChatUser user, MultipartFile imageFile) throws IOException {

//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		user.setUserPassword(BCrypt.hashpw(user.getUserPassword(), BCrypt.gensalt(10)));

		String fileName;
		if (imageFile.isEmpty())
			fileName = "default.png";
		else
			fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
		
		user.setUserDisplayPicture(fileName);
		user.setUserCreationDate(LocalDateTime.now());
		ChatUser savedUser = webClientBuider
				.build()
				.post()
				.uri("http://localhost:9002/user/addUser")
				.body(Mono.just(user), ChatUser.class)
				.retrieve()
				.bodyToMono(ChatUser.class)
				.block();
		System.out.println("Got user :- "+savedUser);
		FileUploadUtil utilFile = new FileUploadUtil();
		String uploadFolder = "src/main/resources/static/images/" + savedUser.getUserId() + "/userProfile";
		utilFile.saveFile(uploadFolder, fileName, imageFile);

		return savedUser;
	}

	@Override
	public boolean authenticateUser(ChatUser user) {
		boolean valid = false;
		ChatUser fetchedUser =  webClientBuider.build().get().uri("http://localhost:9002/user/getUserByEmailId/" + user.getUserEmailId())
				.retrieve().bodyToMono(ChatUser.class).block();

		if (fetchedUser != null
				&& com.nse.webchat.utility.BCrypt.checkpw(user.getUserPassword(), fetchedUser.getUserPassword()))
			valid = true;

		return valid;
	}

	@Override
	public void validateEmail(String userEmailId) throws ChatUserValidationException {
		String userEmailPattern = ChatUserConstants.Validation.USER_EMAIL_ID_PATTERN;

		if (userEmailId == null || userEmailId.isBlank())
			throw new ChatUserValidationException(ChatUserConstants.Validation.USER_EMAIL_EMPTY);

		Pattern p = Pattern.compile(userEmailPattern);
		Matcher m = p.matcher(userEmailId);
		if (!m.matches()) {
			throw new ChatUserValidationException(ChatUserConstants.Validation.USER_EMAIL_ID_INVALID);
		}

	}

	@Override
	public void validateDuplicateEamil(String userEmailId) throws ChatUserValidationException {
		ChatUser user = repo.findByUserEmailIdIgnoreCase(userEmailId);
		if (user != null)
			throw new ChatUserValidationException(
					ChatUserConstants.Validation.USER_EMAIL_ID_REPEATED + " : " + userEmailId);
	}

	@Override
	public void validatePhoneNumber(String phoneNumber) throws ChatUserValidationException {
		String MobileNumberPattern = ChatUserConstants.Validation.USER_PHONE_PATTERN;

		if (phoneNumber == null || phoneNumber.isBlank())
			throw new ChatUserValidationException(ChatUserConstants.Validation.USER_PHONE_EMPTY);

		Pattern pattern = Pattern.compile(MobileNumberPattern);
		Matcher matcher = pattern.matcher(phoneNumber.toString());
		if (!matcher.matches()) {
			throw new ChatUserValidationException(ChatUserConstants.Validation.USER_PHONE_INVALID);
		}

	}

	@Override
	public void validateName(String userFullName) throws ChatUserValidationException {
		String userNamePattern = "^[a-zA-Z0-9 ]+$";

		if (userFullName == null || userFullName.isBlank())
			throw new ChatUserValidationException("Name cannot be empty");

		Pattern p = Pattern.compile(userNamePattern);
		Matcher m = p.matcher(userFullName);
		if (!m.matches()) {
			throw new ChatUserValidationException("Name should only be alphanumeric");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChatUser> getUsers() {
		// TODO Auto-generated method stub
		return  webClientBuider.build().get().uri("http://localhost:9002/user/getAllUsers")
				.retrieve().bodyToMono(List.class).block();
	}

	@Override
	public ChatUser findByUserName(String userName) {
		// TODO Auto-generated method stub
		return  webClientBuider.build().get().uri("http://localhost:9002/user/getUserByUserName/" + userName)
				.retrieve().bodyToMono(ChatUser.class).block();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChatUser> findAllUsers() {
		// TODO Auto-generated method stub
		return webClientBuider.build().get().uri("http://localhost:9002/user/getAllUsers")
				.retrieve().bodyToMono(List.class).block();
	}

	@Override
	public ChatUser finduserById(long userId) {
		// TODO Auto-generated method stub
		return webClientBuider.build().get().uri("http://localhost:9002/user/getUserById/"+userId)
				.retrieve().bodyToMono(ChatUser.class).block();
	}



}
