package com.nse.webchat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nse.webchat.entity.ChatUser;

@Repository
public interface IChatUserRepository extends JpaRepository<ChatUser, Long> {

	ChatUser findByUserEmailIdIgnoreCase(String userEmailId);
	ChatUser findByUserName(String userName);
}
