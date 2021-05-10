package com.nse.webchat.service;



import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nse.webchat.entity.GroupChatMessage;
import com.nse.webchat.model.ChatMessageVO;

public interface IGroupChatMessagingService {

    public List<GroupChatMessage> getAllGroupChatMessages(long groupId);

    public GroupChatMessage addGroupMessage(ChatMessageVO message,long chatId);
    
    public ChatMessageVO saveAttachment(long chatId, ChatMessageVO chatMsg, MultipartFile uploadedAttachment);
    
}
