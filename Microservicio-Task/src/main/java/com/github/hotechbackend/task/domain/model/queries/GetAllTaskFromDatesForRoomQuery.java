package com.github.hotechbackend.task.domain.model.queries;

import java.util.Date;

public record GetAllTaskFromDatesForRoomQuery(String room, Date startDate, Date endDate) {
}
