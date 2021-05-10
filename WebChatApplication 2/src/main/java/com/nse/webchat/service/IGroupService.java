package com.nse.webchat.service;

import com.nse.webchat.entity.GroupInfo;







public interface IGroupService {

	public GroupInfo addGroup(GroupInfo group);

	public GroupInfo findByGroupName(String name);

	public void deleteGroup(String name);

	public GroupInfo findGroupById(long groupId);
	
	public GroupInfo updateGroup (GroupInfo group);
	
	 

}
