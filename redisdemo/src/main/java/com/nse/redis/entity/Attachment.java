package com.nse.redis.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import org.hibernate.annotations.CreationTimestamp;

import org.springframework.stereotype.Component;


import lombok.Data;

@Entity
@Data
@Component
public class Attachment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long attachmentId	;
	private String fileName;		
	private String fileExtension;
	
	private String filePath;		

	@CreationTimestamp
	private LocalDateTime uploadDate;

	private Long uploadedByUserId;
	
}
