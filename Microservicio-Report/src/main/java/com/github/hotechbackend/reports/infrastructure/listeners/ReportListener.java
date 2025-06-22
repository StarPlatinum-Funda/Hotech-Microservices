package com.github.hotechbackend.reports.infrastructure.listeners;

import com.github.hotechbackend.reports.infrastructure.persistence.ReportRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ReportListener {

    private final ReportRepository reportRepository;

    public ReportListener(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    private Map<String, Object> item;

    /*
    {

    }
    */
    @KafkaListener(topics = "report-topic", groupId = "report", containerFactory = "kafkaListenerContainerFactory")
    public void reciveItem(Map<String, Object> val){
        item = val;
        System.out.println(item.toString());

    }
}
