package com.nse.webchat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class GroupChatMessage {

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
}
