package com.nse.webchat.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nse.webchat.entity.GroupChatMessage;

@Repository
public interface GroupChatMessageRepository extends JpaRepository<GroupChatMessage, Long> {
	
	List<GroupChatMessage> findByGroupId(long groupId);
}
