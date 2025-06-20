package com.github.hotech.backend.message.domain.model.aggregates;

import com.github.hotech.backend.message.domain.model.commands.CreateMessageCommand;
import com.github.hotech.backend.message.domain.model.event.MessageCreatedEvent;
import com.github.hotech.backend.message.domain.model.valueobjects.MessageStatus;
import com.github.hotech.backend.message.domain.model.valueobjects.NotificationId;
import com.github.hotech.backend.message.domain.model.valueobjects.UserAccountId;
import com.github.hotech.backend.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Message extends AuditableAbstractAggregateRoot<Message> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    private String receptor;

    @Column(nullable = false)
    @Getter
    private String sender;

    @Column(nullable = false)
    @Getter
    private String content;

    @Embedded
    @Getter
    private MessageStatus status;

    @Embedded
    @Getter
    @AttributeOverrides({
            @AttributeOverride(name = "notificationId", column = @Column(name = "notification_id"))
    })
    private NotificationId notificationId;

    @Embedded
    @Getter
    @AttributeOverrides({
            @AttributeOverride(name = "userAccountId", column = @Column(name = "user_accounts_id"))
    })
    private UserAccountId userAccountId;

    public Message(Long notificationId, Long userAccountId) {
        this.notificationId = new NotificationId(notificationId);
        this.userAccountId = new UserAccountId(userAccountId);
    }

    public Message(CreateMessageCommand command) {
        this.id = command.id();
        this.receptor = command.receptor();
        this.sender = command.sender();
        this.content = command.content();
        this.sendMessage();
        this.notificationId = new NotificationId(command.notificationId());
        this.userAccountId = new UserAccountId(command.userAccountId());
    }

    public Message updateInformation(String receptor, String sender, String content, Long notificationId, Long userAccountId) {
        this.receptor = receptor;
        this.sender = sender;
        this.content = content;
        this.sendMessage();
        this.notificationId = new NotificationId(notificationId);
        this.userAccountId = new UserAccountId(userAccountId);
        return this;
    }

    public void sendMessage() {
        this.status = MessageStatus.SEND;
        this.registerEvent(new MessageCreatedEvent(this, this.getId()));
    }

    public String getNiceCreatedAt() {
        return this.getCreatedAt().toString();
    }

    public void markAsRead() {
        this.status = MessageStatus.READ;
    }
}
