package com.github.hotech.backend.message.domain.model.queries;

public record GetMessagesByNotificationIdAndUserAccountIdQuery(
        Long notificationId,
        Long userAccountId
) {
}
