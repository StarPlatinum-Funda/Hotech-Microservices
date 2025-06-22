package com.github.hotechbackend.reports.application.services;

import com.github.hotechbackend.reports.domain.model.aggregates.Report;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReportQueryService {
    Optional<Report> handle(UUID reportId);
    List<Report> handle();
}