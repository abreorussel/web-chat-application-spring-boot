package com.nse.redis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nse.redis.entity.Conversation;
@Repository
public interface ConversationRepository extends JpaRepository<Conversation,Long> {

	 @Query(value="SELECT * FROM conversation c WHERE (c.from_user = ?1 and c.to_user = ?2) or (c.from_user = ?2 and c.to_user = ?1)",nativeQuery=true)
	 Optional<Conversation> findConversation(long fromUser, long toUser);
}
