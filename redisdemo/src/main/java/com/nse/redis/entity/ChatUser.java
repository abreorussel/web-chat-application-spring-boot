package com.nse.redis.entity;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "groupInfos")
@ToString(exclude = "groupInfos")
@Entity
@Data
@Component
public class ChatUser implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String userFullName;
	private String userEmailId;
	private String userPassword;
	private String userPhoneNumber;
	private String userName;
	
	@CreationTimestamp
	private LocalDateTime userCreationDate;
	
	@UpdateTimestamp
	private LocalDateTime userUpdationDate;
	
	private String userDisplayPicture;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "users")
	@JsonIgnoreProperties("users")
	private Set<GroupInfo> groupInfos = new HashSet<>();
	
	public ChatUser() {
		super();
	}
	
	// parameterized constructor

	public ChatUser(String fullName, String emailId, String phoneNumber, String userName, String password) {
		super();
		this.userFullName = fullName;
		this.userEmailId = emailId;
		this.userPhoneNumber = phoneNumber;
		this.userFullName = userName;
		this.userPassword = password;
		
	}

	// Utility Methods
	public void addGroup(GroupInfo group) {
		this.groupInfos.add(group);
		group.getUsers().add(this);
	}

	public void removeGroup(GroupInfo group) {
		this.groupInfos.remove(group);
		group.getUsers().remove(this);
	}
	
	
	
	
	/*
	 * @Transient public String getUserDisplayPicture() { if (userDisplayPicture ==
	 * null) return null; else return "/src/main/resources/static/images/" + userId
	 * + "/userProfile/" + userDisplayPicture; }
	 */
	 
	 

//updated on

}
