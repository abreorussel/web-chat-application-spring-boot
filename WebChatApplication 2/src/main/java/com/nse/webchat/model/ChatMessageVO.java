package com.nse.webchat.model;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageVO {
  
   private String chatId;
   private long author;
   private String content;
   private String filePath;
   
	/*
	 * private Date timestamp; private MessageStatus status;
	 */
}
