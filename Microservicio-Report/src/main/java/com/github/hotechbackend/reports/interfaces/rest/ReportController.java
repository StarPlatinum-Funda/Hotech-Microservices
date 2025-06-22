package com.github.hotechbackend.reports.interfaces.rest;

import com.github.hotechbackend.reports.application.commands.CreateReportCommand;
import com.github.hotechbackend.reports.application.commands.UpdateReportCommand;
import com.github.hotechbackend.reports.application.services.ReportCommandService;
import com.github.hotechbackend.reports.application.services.ReportQueryService;
import com.github.hotechbackend.reports.domain.model.aggregates.Report;
import com.github.hotechbackend.reports.interfaces.rest.resources.CreateReportResource;
import com.github.hotechbackend.reports.interfaces.rest.resources.ReportResource;
import com.github.hotechbackend.reports.interfaces.rest.resources.UpdateReportResource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportCommandService commandService;
    private final ReportQueryService queryService;

    public ReportController(ReportCommandService commandService, ReportQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public UUID createReport(@RequestBody CreateReportResource resource) {
        return commandService.handle(new CreateReportCommand(resource.title, resource.description));
    }

    @GetMapping
    public List<ReportResource> getAllReports() {
        return queryService.handle().stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ReportResource getReportById(@PathVariable UUID id) {
        return queryService.handle(id)
                .map(this::toResource)
                .orElseThrow(() -> new RuntimeException("Report not found"));
    }

    @PutMapping("/{id}")
    public void updateReport(@PathVariable UUID id, @RequestBody UpdateReportResource resource) {
        commandService.handle(new UpdateReportCommand(id, resource.title, resource.description));
    }

    private ReportResource toResource(Report report) {
        return new ReportResource(
                report.getId(),
                report.getTitle(),
                report.getDescription(),
                report.getStatus(),
                report.getCreatedAt(),
                report.getUpdatedAt()
        );
    }
}