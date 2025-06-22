package com.github.hotechbackend.reports.interfaces.rest.resources;

import com.github.hotechbackend.reports.domain.model.valueobjects.ReportStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReportResource {
    public UUID id;
    public String title;
    public String description;
    public ReportStatus status;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    public ReportResource(UUID id, String title, String description, ReportStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}