package com.nse.webchat.entity;


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
import com.sun.istack.NotNull;

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
