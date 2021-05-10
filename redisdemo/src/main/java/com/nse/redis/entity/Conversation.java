package com.nse.redis.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Conversation implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long conversationId;
	private long fromUser;
	private long toUser;
	private LocalDateTime creationDate;
	public Conversation() {
		
	}
	public Conversation(long conversationId, long fromUser, long toUser, LocalDateTime creationDate) {
		super();
		this.conversationId = conversationId;
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.creationDate = creationDate;
	}
	public long getConversationId() {
		return conversationId;
	}
	public void setConversationId(long conversationId) {
		this.conversationId = conversationId;
	}
	public long getFromUser() {
		return fromUser;
	}
	public void setFromUser(long fromUser) {
		this.fromUser = fromUser;
	}
	public long getToUser() {
		return toUser;
	}
	public void setToUser(long toUser) {
		this.toUser = toUser;
	}
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	@Override
	public String toString() {
		return "Conversation [conversationId=" + conversationId + ", fromUser=" + fromUser + ", toUser=" + toUser
				+ ", creationDate=" + creationDate + "]";
	}
	
}
