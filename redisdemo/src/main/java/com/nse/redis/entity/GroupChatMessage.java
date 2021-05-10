package com.nse.redis.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;



@Entity
public class GroupChatMessage implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long groupMessageId=0;
    private long groupId;
    private long messageAuthorId;
    private String messageAuthor;
    private String messageContent;
    @CreationTimestamp
    private LocalDateTime sentAt;
    private ChatMessage.Status status= ChatMessage.Status.UNREAD;
    private String attachmentPath;

    public enum Status{
        READ,UNREAD
    }
    public GroupChatMessage()
    {
    	
    }
    
	public GroupChatMessage(long groupMessageId, long groupId, long messageAuthorId, String messageAuthor,
			String messageContent, LocalDateTime sentAt, com.nse.redis.entity.ChatMessage.Status status,
			String attachmentPath) {
		super();
		this.groupMessageId = groupMessageId;
		this.groupId = groupId;
		this.messageAuthorId = messageAuthorId;
		this.messageAuthor = messageAuthor;
		this.messageContent = messageContent;
		this.sentAt = sentAt;
		this.status = status;
		this.attachmentPath = attachmentPath;
	}

	public GroupChatMessage(long groupMessageId, long groupId, long messageAuthorId, String messageAuthor,
			String messageContent, LocalDateTime sentAt, com.nse.redis.entity.ChatMessage.Status status) {
		super();
		this.groupMessageId = groupMessageId;
		this.groupId = groupId;
		this.messageAuthorId = messageAuthorId;
		this.messageAuthor = messageAuthor;
		this.messageContent = messageContent;
		this.sentAt = sentAt;
		this.status = status;
	}
	public long getGroupMessageId() {
		return groupMessageId;
	}
	public void setGroupMessageId(long groupMessageId) {
		this.groupMessageId = groupMessageId;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public long getMessageAuthorId() {
		return messageAuthorId;
	}
	public void setMessageAuthorId(long messageAuthorId) {
		this.messageAuthorId = messageAuthorId;
	}
	public String getMessageAuthor() {
		return messageAuthor;
	}
	public void setMessageAuthor(String messageAuthor) {
		this.messageAuthor = messageAuthor;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public LocalDateTime getSentAt() {
		return sentAt;
	}
	public void setSentAt(LocalDateTime sentAt) {
		this.sentAt = sentAt;
	}
	public ChatMessage.Status getStatus() {
		return status;
	}
	public void setStatus(ChatMessage.Status status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "GroupChatMessage [groupMessageId=" + groupMessageId + ", groupId=" + groupId + ", messageAuthorId="
				+ messageAuthorId + ", messageAuthor=" + messageAuthor + ", messageContent=" + messageContent
				+ ", sentAt=" + sentAt + ", status=" + status + "]";
	}
	public String getAttachmentPath() {
		return attachmentPath;
	}
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}
    
}
