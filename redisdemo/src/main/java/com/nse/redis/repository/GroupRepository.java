package com.nse.redis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nse.redis.entity.GroupChatMessage;

@Repository
public interface GroupRepository  extends   JpaRepository<GroupChatMessage,Long>{
	
	List<GroupChatMessage> findByGroupId(long groupId);

}
