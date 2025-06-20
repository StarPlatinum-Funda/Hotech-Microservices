package com.github.hotech.backend.message.infrastructure.persistence.jpa.repositories;

import com.github.hotech.backend.message.domain.model.aggregates.Message;
import com.github.hotech.backend.message.domain.model.valueobjects.NotificationId;
import com.github.hotech.backend.message.domain.model.valueobjects.UserAccountId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    boolean existsByMessage(Long id);
    List<Message> getMessageByNotificationIdAndUserAccountId(NotificationId notificationId, UserAccountId userAccountId);
}
