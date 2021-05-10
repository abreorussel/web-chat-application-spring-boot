package com.nse.redis.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.nse.redis.entity.GroupInfo;



public interface GroupInfoService {

	GroupInfo create(GroupInfo groupinfo);
	GroupInfo update (GroupInfo groupinfo);
    List<GroupInfo>getAll();
	

	void delete(Long id);

	GroupInfo getGroupInfoById(long id);
	
	
	void deleteGroup(GroupInfo groupinfo);
	
	GroupInfo findByGroupName(String name);
	
}
