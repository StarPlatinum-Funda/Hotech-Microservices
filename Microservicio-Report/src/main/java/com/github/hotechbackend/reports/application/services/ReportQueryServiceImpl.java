package com.github.hotechbackend.reports.application.services;

import com.github.hotechbackend.reports.domain.model.aggregates.Report;
import com.github.hotechbackend.reports.infrastructure.persistence.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ReportQueryServiceImpl implements ReportQueryService {

    private final ReportRepository reportRepository;

    public ReportQueryServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Optional<Report> handle(UUID reportId) {
        return reportRepository.findById(reportId);
    }

    @Override
    public List<Report> handle() {
        return reportRepository.findAll();
    }
}