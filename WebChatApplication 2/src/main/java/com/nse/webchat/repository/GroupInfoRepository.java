package com.nse.webchat.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nse.webchat.entity.GroupInfo;






@Repository
public interface GroupInfoRepository extends JpaRepository<GroupInfo, Long> {

	
	GroupInfo findByGroupName(String name);

	
}
