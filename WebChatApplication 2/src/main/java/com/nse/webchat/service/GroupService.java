package com.nse.webchat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nse.webchat.entity.GroupInfo;
import com.nse.webchat.repository.GroupInfoRepository;

import reactor.core.publisher.Mono;






@Service
public class GroupService implements IGroupService{

	@Autowired
	GroupInfoRepository repo;
	
	@Autowired
	WebClient.Builder webClientBuilder;
	
	
	@Override
	public GroupInfo addGroup(GroupInfo group) {
		
		System.out.println("Service "+group.getUsers());
		
		//repo.save(group);
		
		return webClientBuilder.build().post().uri("http://localhost:9002/groupCreate/addGroup").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).body(Mono.just(group), GroupInfo.class).retrieve()
				.bodyToMono(GroupInfo.class).block();
		
		 
	}
	
	


	@Override
	public GroupInfo findByGroupName(String name) {
	
		//repo.findByGroupName(name);
		
		return webClientBuilder.build().get().uri("http://localhost:9002/groupCreate/getGroupByName/"+name).retrieve()
				.bodyToMono(GroupInfo.class).block();
		 
	}


	@Override
	public void deleteGroup(String name) {
		
		//repo.delete(group);
		
		webClientBuilder.build().delete()
		        .uri("http://localhost:9002/groupCreate/deleteGroup/"+name)
		        .retrieve()
		        .bodyToMono(Void.class).block();
		
		
	}

	@Override
	public GroupInfo findGroupById(long groupId) {
		
		//repo.findById(groupId).orElse(null);
		
		return webClientBuilder.build().get().uri("http://localhost:9002/groupCreate/getGroupById/"+groupId).retrieve()
				.bodyToMono(GroupInfo.class).block();
		
		
	}




	@Override
	public GroupInfo updateGroup(GroupInfo group) {
		
		//repo.save(group);
		
		return webClientBuilder.build().put()
		        .uri("http://localhost:9002/groupCreate/updateGroup")
		        .body(Mono.just(group), GroupInfo.class)
		        .retrieve()
		        .bodyToMono(GroupInfo.class).block();
		
	}
}
