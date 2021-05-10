package com.nse.redis.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nse.redis.entity.GroupInfo;
import com.nse.redis.repository.GroupInfoRepository;


@Service
public class GroupInfoServiceImpl implements GroupInfoService {

	private final Logger logger = LoggerFactory.getLogger(GroupInfoServiceImpl.class);

	@Autowired
	private GroupInfoRepository groupinfoRepository;
	
	
	@Override
	public GroupInfo create(GroupInfo groupinfo) {
		logger.debug(" >> GroupInfoService : Entering create");

		GroupInfo groupinfoToRet = groupinfoRepository.save(groupinfo);
		logger.debug(" << GroupInfoService : Exiting create");
		
		System.out.println(groupinfoToRet);
		return groupinfoToRet;
		
	}

	@Override
	public GroupInfo update(GroupInfo groupinfo) {
		logger.debug(">> GroupInfoService : Entering update");
		long id = groupinfo.getGroupId();
		GroupInfo groupinfoInDb = getGroupInfoById(id);
		if (groupinfoInDb != null) {
			logger.debug(">> GroupInfoService : GroupInfo updated : Exiting update");
			return create(groupinfo);
		} else {
			logger.debug(">> GroupInfoService : GroupInfo with this id does not exist : Exiting update");
			return null;
		}
	}

	@Override
	public List<GroupInfo> getAll() {
		logger.debug(" >> GroupInfoService : Entering getAll");
		logger.debug(" << GroupInfoService : Exiting getAll");
		return groupinfoRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		groupinfoRepository.deleteById(id);
		
	}

	@Override
	public GroupInfo getGroupInfoById(long id) {
		logger.debug(" >> GroupInfoService : Entering getGroupInfo");
		Optional<GroupInfo> groupinfoOp = groupinfoRepository.findById(id);

		if (groupinfoOp != null) {
			logger.debug(" << GroupInfoService : Exiting getGroupInfo");
			return groupinfoOp.get();
		} else {
			logger.debug(" << GroupInfoService : No Such GroupInfo Exists : Exiting getGroupInfo");
			return null;
		}
	}

	@Override
	public void deleteGroup(GroupInfo groupinfo) {
		
		groupinfoRepository.delete(groupinfo);
		
	}
	
	public GroupInfo findByGroupName(String name) {
		
		System.out.println(groupinfoRepository.findByGroupName(name));
		
		return groupinfoRepository.findByGroupName(name);
	}
	

}
