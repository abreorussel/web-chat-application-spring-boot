package com.nse.redis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nse.redis.entity.GroupInfo;

@Repository
public interface GroupInfoRepository extends JpaRepository<GroupInfo, Long> {
	
	GroupInfo findByGroupName(String groupName);

}
