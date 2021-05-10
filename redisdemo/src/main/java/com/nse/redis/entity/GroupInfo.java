package com.nse.redis.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude = "users")
@ToString(exclude = "users")
@Entity
public class GroupInfo implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long groupId;
	private String groupName;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "user_group", joinColumns = { @JoinColumn(name = "group_id") }, inverseJoinColumns = {
			@JoinColumn(name = "user_id") })
	@JsonIgnoreProperties("groupInfos")
	private Set<ChatUser> users = new HashSet<>();
	
	


	// parameterized constructor
	public GroupInfo(String groupName) {
		super();
		this.groupName = groupName;
	}

	// Utility Methods
	public void addUser(ChatUser user) {
		this.getUsers().add(user);
		user.getGroupInfos().add(this);
	}

	public void removeUser(ChatUser user) {
		this.getUsers().remove(user);
		user.getGroupInfos().remove(this);
	}

}
