package com.nse.webchat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nse.webchat.entity.Attachment;
import com.nse.webchat.entity.ChatUser;

@Repository
public interface IAttachmentRepository extends JpaRepository<Attachment, Long> {

}
