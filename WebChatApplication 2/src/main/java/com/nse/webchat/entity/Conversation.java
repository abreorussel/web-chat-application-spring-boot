package com.nse.webchat.entity;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Conversation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long conversationId=0;
	private long fromUser;
	private long toUser;
	private LocalDateTime creationDate;
	
}
