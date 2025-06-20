package com.github.hotech.inventory.application.commandservices;

import com.github.hotech.inventory.domain.model.aggregates.Warehouse;
import com.github.hotech.inventory.domain.model.commands.CreateWarehouseCommand;
import com.github.hotech.inventory.domain.model.commands.UpdateWarehouseCommand;
import com.github.hotech.inventory.domain.services.WarehouseCommandService;
import com.github.hotech.inventory.infrastructure.persistence.jpa.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarehouseCommandServiceImpl implements WarehouseCommandService {

    private final WarehouseRepository  warehouseRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public WarehouseCommandServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public Optional<Warehouse> handle(CreateWarehouseCommand command) {

        var warehouse = new Warehouse(command);
        var createdWarehouse = warehouseRepository.save(warehouse);
        return Optional.of(createdWarehouse);
    }

    @Override
    public Optional<Warehouse> handle(UpdateWarehouseCommand command) {
        var result = warehouseRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Warehouse does not exist");
        var warehouse =  result.get();

        try{
            var updatedWarehouse = warehouseRepository.save(warehouse.updateWarehouse(command));
            return Optional.of(updatedWarehouse);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating Warehouse: " + e.getMessage());
        }
    }
}
