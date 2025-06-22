package com.github.hotechbackend.reports.application.commands;

import java.util.UUID;

public record UpdateReportCommand(
        UUID reportId,
        String title,
        String description
) {}