package com.github.hotechbackend.reports.domain.model.aggregates;


import com.github.hotechbackend.reports.domain.model.valueobjects.ReportStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    private UUID id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // JPA requiere constructor vacío
    protected Report() {}

    public Report(UUID id, String title, String description, ReportStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = null;
    }

    // getters y setters (si usas Lombok puedes @Getter y @Setter)

    public UUID getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public ReportStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void update(String title, String description) {
        this.title = title;
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public void approve() {
        this.status = ReportStatus.APPROVED;
        this.updatedAt = LocalDateTime.now();
    }

    public void reject() {
        this.status = ReportStatus.REJECTED;
        this.updatedAt = LocalDateTime.now();
    }
}