package com.nse.redis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nse.redis.entity.ChatUser;


@Repository
public interface UserRepository extends JpaRepository<ChatUser,Long>{
	ChatUser findByUserEmailIdIgnoreCase(String userEmailId);
	ChatUser findByUserName(String userName);
}
