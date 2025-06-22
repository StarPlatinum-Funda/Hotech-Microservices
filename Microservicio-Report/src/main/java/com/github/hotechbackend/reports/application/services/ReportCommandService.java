package com.github.hotechbackend.reports.application.services;

import com.github.hotechbackend.reports.application.commands.CreateReportCommand;
import com.github.hotechbackend.reports.application.commands.UpdateReportCommand;
import com.github.hotechbackend.reports.domain.model.aggregates.Report;

import java.util.UUID;

public interface ReportCommandService {
    UUID handle(CreateReportCommand command);
    void handle(UpdateReportCommand command);
}
