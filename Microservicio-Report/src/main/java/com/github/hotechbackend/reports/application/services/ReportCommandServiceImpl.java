package com.github.hotechbackend.reports.application.services;

import com.github.hotechbackend.reports.application.commands.CreateReportCommand;
import com.github.hotechbackend.reports.application.commands.UpdateReportCommand;
import com.github.hotechbackend.reports.domain.model.aggregates.Report;
import com.github.hotechbackend.reports.infrastructure.persistence.ReportRepository;
import com.github.hotechbackend.reports.domain.model.valueobjects.ReportStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ReportCommandServiceImpl implements ReportCommandService {

    private final ReportRepository reportRepository;

    public ReportCommandServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public UUID handle(CreateReportCommand command) {
        Report newReport = new Report(
                UUID.randomUUID(),
                command.description(),
                command.title(),
                ReportStatus.DRAFT,
                LocalDateTime.now()
        );

        reportRepository.save(newReport);
        return newReport.getId();
    }

    @Override
    public void handle(UpdateReportCommand command) {
        reportRepository.findById(command.reportId()).ifPresent(report -> {
            report.update(command.description(),command.title());
            reportRepository.save(report);
        });
    }
}