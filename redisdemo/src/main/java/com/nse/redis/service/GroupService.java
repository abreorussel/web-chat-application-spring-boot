package com.nse.redis.service;

import java.util.List;

import com.nse.redis.entity.GroupChatMessage;

public interface GroupService {

	
GroupChatMessage create(GroupChatMessage groupchatmessage);

	List<GroupChatMessage> getAllGroupChatMessages(long groupId);

	void delete(Long id);

	GroupChatMessage getGroupById(long id);
}
