package com.github.hotechbackend.reports.application.commands;

import java.util.UUID;

public record CreateReportCommand(
        String title,
        String description
) {}
