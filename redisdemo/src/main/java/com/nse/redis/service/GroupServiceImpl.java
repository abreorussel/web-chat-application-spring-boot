package com.nse.redis.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nse.redis.entity.GroupChatMessage;

import com.nse.redis.repository.GroupRepository;


@Service
public class GroupServiceImpl implements GroupService{

	private final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

	@Autowired
	private GroupRepository groupRepository;
	
	
	// Used
	@Override
	public GroupChatMessage create(GroupChatMessage groupchatmessage) {
		logger.debug(" >> GroupService : Entering create");

		GroupChatMessage groupToRet = groupRepository.save(groupchatmessage);
		logger.debug(" << GroupService : Exiting create");
		return groupToRet;
	}

	@Override
	public void delete(Long id) {
		groupRepository.deleteById(id);
		
	}

	@Override
	public GroupChatMessage getGroupById(long id) {
		logger.debug(" >> GroupService : Entering getGroup");
		Optional<GroupChatMessage> groupchatmessageOp = groupRepository.findById(id);

		if (groupchatmessageOp != null) {
			logger.debug(" << GroupService : Exiting getGroup");
			return groupchatmessageOp.get();
		} else {
			logger.debug(" << GroupService : No Such Group Exists : Exiting getGroup");
			return null;
		}
	}

	public List<GroupChatMessage> getAll() {
		logger.debug(" >> GroupService : Entering getAll");
		logger.debug(" << GroupService : Exiting getAll");
		return groupRepository.findAll();
	}

	
	// Used
	@Override
	public List<GroupChatMessage> getAllGroupChatMessages(long groupId) {
		
		return groupRepository.findByGroupId(groupId);
	}

}
