package com.nse.redis.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nse.redis.entity.GroupInfo;

import com.nse.redis.service.GroupInfoServiceImpl;



@RestController
@RequestMapping("/groupCreate")
public class GroupInfoController {
	
private final Logger logger = LoggerFactory.getLogger(GroupInfoController.class);
	
	@Autowired
	private GroupInfoServiceImpl groupinfoService;
	
	
	// Used
	//@CachePut(value = "groups", key = "#groupinfo.groupId")
	@PostMapping(value = "/addGroup")
	public GroupInfo create(@RequestBody GroupInfo groupinfo) {
		logger.debug(" >> GroupInfoController : /groupinfo : ", groupinfo.toString());
		
		System.out.println("Reached here");
		
		System.out.println(groupinfo);
		return groupinfoService.create(groupinfo);
	}

	//Used
	@GetMapping("/getGroupById/{id}")
	//@Cacheable(value = "groups", key = "#id")
	public GroupInfo getGroupInfoById(@PathVariable long id) {
		logger.debug(" >> GroupInfoController : /groupinfo/{} call : ", id);

		return groupinfoService.getGroupInfoById(id);

	}

//	@DeleteMapping("/delete/{id}")
//	//@CacheEvict(value = "groups", allEntries = false, key = "#id")
//	public void deleteById(@PathVariable Long id) {
//		logger.debug(" >> GroupInfoController : /delete : ", id);
//		groupinfoService.delete(id);
//		logger.debug(" << GroupInfoController : /delete : ", id);
//
//	}
	
//	@GetMapping("/groupinfos")
//	public List<GroupInfo> getAll() {
//		logger.debug(" >> GroupInfoController : /groupinfos : ");
//
//		return groupinfoService.getAll();
//	}

	// Used
	@PutMapping("/updateGroup")
	//@CachePut(value = "groups", key = "#id")
	public GroupInfo updateGroupInfo(@RequestBody GroupInfo groupinfo) {
		logger.debug(" >> GroupInfoController : /update : ", groupinfo.toString());
		return groupinfoService.update(groupinfo);
	}
	
	
	
	@DeleteMapping("/deleteGroup/{name}")
	//@CacheEvict(value = "groups", key = #name)
	public void deleteGroup(@PathVariable String name) {
		logger.debug(" >> GroupInfoController : /delete : ");
		
		
		groupinfoService.deleteGroup(groupinfoService.findByGroupName(name));
		logger.debug(" << GroupInfoController : /delete : ");

	}
	
	// Used
	@GetMapping("/getGroupByName/{name}")
	//@Cacheable(value = "groups", key = "#name")
	public GroupInfo findByGroupName(@PathVariable("name") String name) {
		logger.debug(" >> GroupInfoController : /groupinfo/{} call : ", name);

		//System.out.println(groupinfoService.findByGroupName(name).getUsers());
		
		return groupinfoService.findByGroupName(name);

	}
	
	
	
	
}
