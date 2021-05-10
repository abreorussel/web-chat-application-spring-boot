package com.nse.webchat.entity;

import java.time.LocalDateTime;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class ChatMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long messageId=0;
	private long conversationId;
	private long messageAuthorId;
	private String messageContent;
	@CreationTimestamp
	private LocalDateTime sentAt;
	private Status status=Status.UNREAD;	
	private String attachmentPath;

public enum Status{
	READ,UNREAD
}
}
