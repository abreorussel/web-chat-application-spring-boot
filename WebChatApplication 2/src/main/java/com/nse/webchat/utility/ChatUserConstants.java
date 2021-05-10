package com.nse.webchat.utility;

public final class ChatUserConstants {

	public static final class Validation {

		public static final String USER = "user";
		public static final String USERS = "users";

		public static final String USER_FULL_NAME = "userFullNameValidation";

		public static final String USER_EMAIL_ID = "userEmailIdValidation";
		public static final String USER_EMAIL_ID_PATTERN = "^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,6}$";
		public static final String USER_EMAIL_ID_INVALID = "Email id not valid!";
		public static final String USER_EMAIL_ID_REPEATED = "User already exists with the Email id";
		public static final String USER_EMAIL_EMPTY = "Email id cannot be empty";

		public static final String USER_PHONE = "userPhoneNumberValidation";
		public static final String USER_PHONE_PATTERN = "^[789]\\d{9}$";
		public static final String USER_PHONE_INVALID = "Mobile number should contain 10 digits exactly and should start with 7,8 or 9.";
		public static final String USER_PHONE_EMPTY = "Phone number cannot be empty";

		public static final String USER_DISPLAY_PICTURE = "userDisplayPictureValidation";
	}

	public static final class User {

		public static final String USER_NOT_FOUND = "User not found with the id";
		public static final String USER_DISABLED = "User is Disabled!";

	}

	public static final class Registration {

		public static final String LOGIN_SUCCESS = "Login Successful!";
		public static final String LOGIN_FAIL = "loginFail";
		public static final String LOGIN_FAIL_MESSAGE = "Login Fail!";
		public static final String WRONG_CREDENTIALS = "Wrong Credentials";
		public static final String WRONG_DATA = "Please check the data passed!";
		public static final String REGISTRATION_SUCCESSFULL = "registrationSucessfull";
		public static final String REGISTRATION_SUCCESSFULL_MESSAGE = "Registration Sucessfull!!";

	}

	public static final class Response {

		public static final String ERROR = "error";
		public static final String MESSAGE = "message";
		public static final String URL = "url";

	}

}
